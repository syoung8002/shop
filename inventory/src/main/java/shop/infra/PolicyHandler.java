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
    InventoryRepository inventoryRepository;

    @Autowired
    InventoryPolicy inventoryPolicy;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverShipped_DecreaseStock(@Payload Shipped shipped) {
        if (!shipped.validate()) return;
        Shipped event = shipped;
        System.out.println(
            "\n\n##### listener DecreaseStock : " + shipped.toJson() + "\n\n"
        );

        // Sample Logic //
        inventoryPolicy.decreaseStock(event);
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderPlaced_CheckInventory(
        @Payload OrderPlaced orderPlaced
    ) {
        if (!orderPlaced.validate()) return;
        OrderPlaced event = orderPlaced;
        System.out.println(
            "\n\n##### listener CheckInventory : " +
            orderPlaced.toJson() +
            "\n\n"
        );

        // Sample Logic //
        inventoryPolicy.checkInventory(event);
    }

    @Autowired
    shop.external.OrderService orderService;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverReturned_UpdateStock(@Payload Returned returned) {
        if (!returned.validate()) return;
        Returned event = returned;
        System.out.println(
            "\n\n##### listener UpdateStock : " + returned.toJson() + "\n\n"
        );

        // REST Request Sample

        // orderService.getOrder(/** mapping value needed */);

        // Sample Logic //
        inventoryPolicy.updateStock(event);
    }
}
