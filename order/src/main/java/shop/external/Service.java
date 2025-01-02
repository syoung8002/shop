package shop.external;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

@FeignClient(name="order", url="http://order:8080")
public interface Service {
    @RequestMapping(method= RequestMethod.POST, path="/")
    public void order(@RequestBody  );

}

