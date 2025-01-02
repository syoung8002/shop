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
    ShippingRepository shippingRepository;

    @Autowired
    ShippingPolicy shippingPolicy;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @Autowired
    shop.external.CustomerService customerService;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderPlaced_StartDelivery(
        @Payload OrderPlaced orderPlaced
    ) {
        if (!orderPlaced.validate()) return;
        OrderPlaced event = orderPlaced;
        System.out.println(
            "\n\n##### listener StartDelivery : " +
            orderPlaced.toJson() +
            "\n\n"
        );

        // REST Request Sample

        // customerService.getCustomer(/** mapping value needed */);

        // Sample Logic //
        shippingPolicy.startDelivery(event);
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCancelled_CancelDelivery(
        @Payload OrderCancelled orderCancelled
    ) {
        if (!orderCancelled.validate()) return;
        OrderCancelled event = orderCancelled;
        System.out.println(
            "\n\n##### listener CancelDelivery : " +
            orderCancelled.toJson() +
            "\n\n"
        );

        // Sample Logic //
        shippingPolicy.cancelDelivery(event);
    }
}
