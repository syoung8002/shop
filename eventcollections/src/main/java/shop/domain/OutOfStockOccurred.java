package shop.domain;

import java.util.*;
import javax.validation.constraints.*;
import lombok.*;
import shop.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
public class OutOfStockOccurred extends AbstractEvent {

    private Long id;
}
//>>> DDD / Domain Event
