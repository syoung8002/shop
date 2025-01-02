package shop.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import shop.config.kafka.KafkaProcessor;
import shop.domain.*;

@Service
public class EventCollectorViewHandler {

    //<<< DDD / CQRS
    @Autowired
    private EventCollectorRepository eventCollectorRepository;

    private final Validator validator;

    public EventCollectorViewHandler() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }

    private String getCorrelationKey(JsonNode jsonNode, String eventType) {
        switch (eventType) {
            case "OrderPlaced":
                return jsonNode.get("id").asText();
            case "OrderCancelled":
                return jsonNode.get("id").asText();
            case "Shipped":
                return jsonNode.get("orderId").asText();
            case "Returned":
                return jsonNode.get("orderId").asText();
            default:
                return "Unknown";
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void onEventReceived(@Payload String rawPayload) {
        try {
            // _type 속성 파싱하여 대상 클래스 타입 결정
            ObjectMapper typeMapper = new ObjectMapper();
            JsonNode jsonNode = typeMapper.readTree(rawPayload);

            // _type 속성 추출
            String type = jsonNode.get("eventType").asText();

            // 대상 클래스 동적 로드
            Class<?> eventClass = Class.forName("shop.domain." + type);

            // 페이로드를 대상 클래스에 역직렬화
            ObjectMapper objectMapper = new ObjectMapper();
            Object event = objectMapper.readValue(rawPayload, eventClass);

            // 이벤트 검증
            Set<ConstraintViolation<Object>> violations = validator.validate(
                event
            );
            if (violations.isEmpty()) {
                EventCollector eventCollector = new EventCollector();
                eventCollector.setType(type);
                eventCollector.setCorrelationKey(
                    getCorrelationKey(jsonNode, type)
                );
                eventCollector.setPayload(rawPayload);
                eventCollector.setTimestamp(jsonNode.get("timestamp").asLong());

                // 유효한 이벤트 저장
                eventCollectorRepository.save(eventCollector);
            } else {
                throw new Exception("Invalid event: " + violations);
            }
        } catch (Exception e) {
            try {
                // 필드 검증 비활성화 후 재파싱 시도
                ObjectMapper relaxedMapper = new ObjectMapper();
                relaxedMapper.configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false
                );

                // 다시 파싱하여 가능한 데이터 추출
                JsonNode jsonNode = new ObjectMapper().readTree(rawPayload);
                String type = jsonNode.get("eventType").asText();

                // 부분 데이터로 EventCollector 생성
                EventCollector eventCollector = new EventCollector();
                eventCollector.setType(type);
                eventCollector.setPayload(rawPayload);
                eventCollector.setCorrelationKey(
                    getCorrelationKey(jsonNode, type)
                );
                eventCollector.setTimestamp(
                    jsonNode.has("timestamp")
                        ? jsonNode.get("timestamp").asLong()
                        : System.currentTimeMillis()
                );
                eventCollector.setError(e.getMessage());

                // 부분 데이터 저장
                eventCollectorRepository.save(eventCollector);
            } catch (Exception innerException) {
                // 재파싱도 실패한 경우 처리
                EventCollector eventCollector = new EventCollector();
                eventCollector.setType("UnknownType");
                eventCollector.setPayload(rawPayload);
                eventCollector.setCorrelationKey("Unknown");
                eventCollector.setTimestamp(System.currentTimeMillis());
                eventCollector.setError(innerException.getMessage());

                // 최종 데이터 저장
                eventCollectorRepository.save(eventCollector);

                innerException.printStackTrace();
            }

            // 원래 예외 로그 기록
            e.printStackTrace();
        }
    }
    //>>> DDD / CQRS
}
