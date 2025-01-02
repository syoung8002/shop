package shop.domain;

import java.util.Date;
import shop.domain.*;
import shop.infra.AbstractEvent;

public class OutOfStockOccurred extends AbstractEvent {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
