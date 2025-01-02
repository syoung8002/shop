package shop.domain;

import java.util.*;
import javax.validation.constraints.*;
import lombok.*;
import shop.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
public class OrderCancelled extends AbstractEvent {

    @NotNull
    private Long id;

    private String productId;
    private Integer qty;
    private String customerId;
}
//>>> DDD / Domain Event
