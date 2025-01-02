package shop.infra;

import shop.domain.*;
import shop.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class OrderStatusViewHandler {


    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderPlaced_then_CREATE_1 (@Payload OrderPlaced orderPlaced) {
        try {

            if (!orderPlaced.validate()) return;

            // view 객체 생성
            OrderStatus orderStatus = new OrderStatus();
            // view 객체에 이벤트의 Value 를 set 함
        Id
            orderStatus.setId(orderPlaced.getId());
        OrderStatus
            orderStatus.setOrderStatus("OrderPlaced");
        ProductId
            orderStatus.setProductId(orderPlaced.getProductId());
            // view 레파지 토리에 save
            orderStatusRepository.save(orderStatus);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenShipped_then_UPDATE_1(@Payload Shipped shipped) {
        try {
            if (!shipped.validate()) return;
                // view 객체 조회
            Optional<OrderStatus> orderStatusOptional = orderStatusRepository.findById(shipped.getOrderId());

            if( orderStatusOptional.isPresent()) {
                 OrderStatus orderStatus = orderStatusOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 orderStatus.setDeliveryStatus("Shipped");
                // view 레파지 토리에 save
                 orderStatusRepository.save(orderStatus);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderCancelled_then_DELETE_1(@Payload OrderCancelled orderCancelled) {
        try {
            if (!orderCancelled.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            orderStatusRepository.deleteById(orderCancelled.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

