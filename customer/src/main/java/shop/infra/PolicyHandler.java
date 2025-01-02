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
    CustomerRepository customerRepository;

    @Autowired
    CustomerPolicy customerPolicy;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderPlaced_ThankToCustomer(
        @Payload OrderPlaced orderPlaced
    ) {
        if (!orderPlaced.validate()) return;
        OrderPlaced event = orderPlaced;
        System.out.println(
            "\n\n##### listener ThankToCustomer : " +
            orderPlaced.toJson() +
            "\n\n"
        );

        // Sample Logic //
        customerPolicy.thankToCustomer(event);
    }
}
