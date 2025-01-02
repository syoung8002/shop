package shop.domain.usecase;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.domain.*;

@Service
public class CustomerPolicy {

    @Autowired
    CustomerRepository customerRepository;

    public void thankToCustomer(OrderPlaced orderPlaced) {
        Customer customer = new Customer();
        /*
        LOGIC GOES HERE
        */
        // customerRepository.save(customer);

    }
}
