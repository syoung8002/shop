package shop.domain.usecase;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.domain.*;

@Service
public class InventoryPolicy {

    @Autowired
    InventoryRepository inventoryRepository;

    public void decreaseStock(Shipped shipped) {
        Inventory inventory = new Inventory();
        /*
        LOGIC GOES HERE
        */
        // inventoryRepository.save(inventory);

    }

    public void checkInventory(OrderPlaced orderPlaced) {
        Inventory inventory = new Inventory();
        /*
        LOGIC GOES HERE
        */
        // inventoryRepository.save(inventory);

    }

    public void updateStock(Returned returned) {
        Inventory inventory = new Inventory();
        /*
        LOGIC GOES HERE
        */
        // inventoryRepository.save(inventory);

    }
}
