package shop.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import shop.InventoryApplication;
import shop.domain.OutOfStockOccurred;

@Entity
@Table(name = "Inventory_table")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long stock;

    @PostPersist
    public void onPostPersist() {
        OutOfStockOccurred outOfStockOccurred = new OutOfStockOccurred();
        BeanUtils.copyProperties(this, outOfStockOccurred);
        outOfStockOccurred.publishAfterCommit();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }
}
