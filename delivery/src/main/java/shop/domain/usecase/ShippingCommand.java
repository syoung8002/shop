package shop.domain.usecase;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.ShippingApplication;
import shop.domain.*;

@Service
public class ShippingCommand {

    @Autowired
    ShippingRepository shippingRepository;
}
