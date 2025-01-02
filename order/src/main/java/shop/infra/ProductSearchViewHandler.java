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
public class ProductSearchViewHandler {


    @Autowired
    private ProductSearchRepository productSearchRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenOrderPlaced_then_CREATE_1 (@Payload OrderPlaced orderPlaced) {
        try {

            if (!orderPlaced.validate()) return;

            // view 객체 생성
            ProductSearch productSearch = new ProductSearch();
            // view 객체에 이벤트의 Value 를 set 함
        Id
            productSearch.setId(orderPlaced.getId());
        ProductId
            productSearch.setProductId(orderPlaced.getProductId());
        OrderStatus
            productSearch.setOrderStatus("OrderAccepted");
            // view 레파지 토리에 save
            productSearchRepository.save(productSearch);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @StreamListener(KafkaProcessor.INPUT)
    public void whenShipped_then_UPDATE_1(@Payload Shipped shipped) {
        try {
            if (!shipped.validate()) return;
                // view 객체 조회
            Optional<ProductSearch> productSearchOptional = productSearchRepository.findById(shipped.getOrderId());

            if( productSearchOptional.isPresent()) {
                 ProductSearch productSearch = productSearchOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                 productSearch.setDeliveryStatus("배송출발함"");
                // view 레파지 토리에 save
                 productSearchRepository.save(productSearch);
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
            productSearchRepository.deleteById(orderCancelled.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

