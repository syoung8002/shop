package shop.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import shop.domain.*;

@RepositoryRestResource(collectionResourceRel = "customers", path = "customers")
public interface CustomerRepository
    extends PagingAndSortingRepository<Customer, Long> {}
