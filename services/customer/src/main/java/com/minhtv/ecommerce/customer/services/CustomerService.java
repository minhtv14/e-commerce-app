package com.minhtv.ecommerce.customer.services;

import com.minhtv.ecommerce.customer.requests.CustomerRequest;
import org.springframework.stereotype.Service;

@Service
public interface CustomerService {
    String createCustomer(CustomerRequest request);
    void updateCustomer(CustomerRequest request);
}
