package shop.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import shop.CustomerApplication;

@Entity
@Table(name = "Customer_table")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
