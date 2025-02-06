package com.minhtv.ecommerce.customer.mappers;

import com.minhtv.ecommerce.customer.entities.Customer;
import com.minhtv.ecommerce.customer.requests.CustomerRequest;
import com.minhtv.ecommerce.customer.responses.CustomerResponse;
import org.springframework.stereotype.Component;

/**
 * CustomerMapper is a class that provides methods to map between CustomerRequest and Customer
 */
@Component
public class CustomerMapper {

    /**
     * Map from CustomerRequest to Customer
     * @param request CustomerRequest
     * @return Customer
     */
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

    /**
     * Map from Customer to CustomerResponse
     * @param customer Customer
     * @return CustomerResponse
     */
    public CustomerResponse fromCustomer(Customer customer) {
        if(customer == null) {
            return null;
        }
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
