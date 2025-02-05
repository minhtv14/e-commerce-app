package com.minhtv.ecommerce.customer.mappers;

import com.minhtv.ecommerce.customer.entities.Customer;
import com.minhtv.ecommerce.customer.requests.CustomerRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request) {
        if(request == null) {
            return null;
        }
        return Customer.builder()
                .id(request.id())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .build();
    }
}
