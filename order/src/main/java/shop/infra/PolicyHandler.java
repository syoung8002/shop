package shop.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import shop.config.kafka.KafkaProcessor;
import shop.domain.*;
import shop.domain.usecase.*;

@Service
public class PolicyHandler {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderPolicy orderPolicy;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOutOfStockOccurred_ApologizeCustomer(
        @Payload OutOfStockOccurred outOfStockOccurred
    ) {
        if (!outOfStockOccurred.validate()) return;
        OutOfStockOccurred event = outOfStockOccurred;
        System.out.println(
            "\n\n##### listener ApologizeCustomer : " +
            outOfStockOccurred.toJson() +
            "\n\n"
        );

        // Sample Logic //
        orderPolicy.apologizeCustomer(event);
    }
}
