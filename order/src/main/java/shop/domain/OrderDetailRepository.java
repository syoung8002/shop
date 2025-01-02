package shop.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import shop.domain.*;

@RepositoryRestResource(
    collectionResourceRel = "orderdetails",
    path = "orderdetails"
)
public interface OrderDetailRepository
    extends PagingAndSortingRepository<OrderDetail, Long> {}
