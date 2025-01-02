package shop.domain;

import java.util.Date;
import shop.domain.*;
import shop.infra.AbstractEvent;

public class Shipped extends AbstractEvent {

    private Long id;
    private Long orderId;

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
}
