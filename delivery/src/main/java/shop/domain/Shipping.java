package shop.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import shop.DeliveryApplication;
import shop.domain.Returned;
import shop.domain.Shipped;

@Entity
@Table(name = "Shipping_table")
public class Shipping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orderId;

    private String productId;

    @PostPersist
    public void onPostPersist() {
        Shipped shipped = new Shipped();
        BeanUtils.copyProperties(this, shipped);
        shipped.publishAfterCommit();
    }

    @PostRemove
    public void onPostRemove() {
        Returned returned = new Returned();
        BeanUtils.copyProperties(this, returned);
        returned.publishAfterCommit();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
