package com.minhtv.ecommerce.customer.services;

import com.minhtv.ecommerce.customer.requests.CustomerRequest;
import com.minhtv.ecommerce.customer.responses.CustomerResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CustomerService {

    String createCustomer(CustomerRequest request);

    void updateCustomer(CustomerRequest request);

    List<CustomerResponse> getCustomersByCondition();

    Boolean exitsById(String customerId);

    CustomerResponse getCustomerById(String customerId);

    void deleteCustomerById(String customerId);
}