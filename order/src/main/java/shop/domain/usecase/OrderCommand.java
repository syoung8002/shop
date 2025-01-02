package shop.domain.usecase;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.OrderApplication;
import shop.domain.*;

@Service
public class OrderCommand {

    @Autowired
    OrderRepository orderRepository;
}
