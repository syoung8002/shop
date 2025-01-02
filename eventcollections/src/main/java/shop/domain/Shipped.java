package shop.domain;

import java.util.*;
import javax.validation.constraints.*;
import lombok.*;
import shop.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
public class Shipped extends AbstractEvent {

    private Long id;

    @NotNull
    private Long orderId;
}
//>>> DDD / Domain Event
