package shop.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="delivery", url="http://delivery:8080", fallback=ServiceImpl.class)
public interface Service {
    @RequestMapping(method= RequestMethod.GET, path="//{undefined}")
    public  get(@PathVariable("")  );

}

