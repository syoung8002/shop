package shop.infra;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import shop.domain.*;

public interface ProductSearchRepository
    extends CrudRepository<ProductSearch, Long> {}
