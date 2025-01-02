package shop.domain.usecase;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.CustomerApplication;
import shop.domain.*;

@Service
public class CustomerCommand {

    @Autowired
    CustomerRepository customerRepository;
}
