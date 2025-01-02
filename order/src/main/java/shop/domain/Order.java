package shop.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import shop.OrderApplication;
import shop.domain.OrderCancelled;
import shop.domain.OrderPlaced;

@Entity
@Table(name = "Order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String productId;

    private Integer qty;

    private String customerId;

    @OneToMany
    private OrderDetail orderDetail;

    @Embedded
    private Payment payment;

    @Embedded
    private Money price;

    @PostPersist
    public void onPostPersist() {
        OrderPlaced orderPlaced = new OrderPlaced();
        BeanUtils.copyProperties(this, orderPlaced);
        orderPlaced.publishAfterCommit();
    }

    @PostRemove
    public void onPostRemove() {
        OrderCancelled orderCancelled = new OrderCancelled();
        BeanUtils.copyProperties(this, orderCancelled);
        orderCancelled.publishAfterCommit();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public OrderDetail getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }
}
