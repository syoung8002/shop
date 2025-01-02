package shop.domain.usecase;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.domain.*;

@Service
public class ShippingPolicy {

    @Autowired
    ShippingRepository shippingRepository;

    public void startDelivery(OrderPlaced orderPlaced) {
        Shipping shipping = new Shipping();
        /*
        LOGIC GOES HERE
        */
        // shippingRepository.save(shipping);

    }

    public void cancelDelivery(OrderCancelled orderCancelled) {
        Shipping shipping = new Shipping();
        /*
        LOGIC GOES HERE
        */
        // shippingRepository.save(shipping);

    }
}
