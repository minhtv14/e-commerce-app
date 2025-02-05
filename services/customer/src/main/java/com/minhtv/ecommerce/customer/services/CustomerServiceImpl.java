package com.minhtv.ecommerce.customer.services;

import com.minhtv.ecommerce.customer.entities.Customer;
import com.minhtv.ecommerce.customer.exceptions.CustomerNotFoundException;
import com.minhtv.ecommerce.customer.mappers.CustomerMapper;
import com.minhtv.ecommerce.customer.repositories.CustomerRepository;
import com.minhtv.ecommerce.customer.requests.CustomerRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    @Override
    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    @Override
    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot update customer:: Not customer found with the provided ID:: %s", request.id())
                ));
        repository.save(customer);

        mergerCustomer(customer, request);
    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        if(StringUtils.isNotBlank(request.firstName())) {
            customer.setFirstName(request.firstName());
        }
        if(StringUtils.isNotBlank(request.lastName())) {
            customer.setLastName(request.lastName());
        }
        if(StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }
        if(request.address() != null) {
            customer.setAddress(request.address());
        }

    }
}
