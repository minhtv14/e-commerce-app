package com.minhtv.ecommerce.customer.services;

import com.minhtv.ecommerce.customer.entities.Customer;
import com.minhtv.ecommerce.customer.exceptions.CustomerNotFoundException;
import com.minhtv.ecommerce.customer.mappers.CustomerMapper;
import com.minhtv.ecommerce.customer.repositories.CustomerRepository;
import com.minhtv.ecommerce.customer.requests.CustomerRequest;
import com.minhtv.ecommerce.customer.responses.CustomerResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Customer service implementation
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    // TODO: Inject CustomerRepository
    private final CustomerRepository repository;

    // TODO: Inject CustomerMapper
    private final CustomerMapper mapper;

    /**
     * Create a new customer
     *
     * @param request CustomerRequest
     * @return String
     */
    @Override
    public String createCustomer(CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }

    /**
     * Update a customer
     *
     * @param request CustomerRequest
     */
    @Override
    public void updateCustomer(CustomerRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot update customer:: Not customer found with the provided ID:: %s", request.id())
                ));
        repository.save(customer);

        mergerCustomer(customer, request);
    }

    /**
     * Get customers by condition
     *
     * @return List<CustomerResponse>
     */
    @Override
    public List<CustomerResponse> getCustomersByCondition() {
        return repository.findAll().stream()
                .map(mapper::fromCustomer)
                .toList();
    }

    /**
     * Check if customer exists by id
     *
     * @param customerId String
     * @return Boolean
     */
    @Override
    public Boolean exitsById(String customerId) {
        return repository.findById(customerId).isPresent();
    }

    /**
     * Get customer by id
     *
     * @param customerId String
     * @return CustomerResponse
     */
    @Override
    public CustomerResponse getCustomerById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Cannot find customer with the provided ID:: %s", customerId)
                ));
    }

    /**
     * Delete customer by id
     *
     * @param customerId String
     */
    @Override
    public void deleteCustomerById(String customerId) {
        repository.deleteById(customerId);
    }

    /**
     * Merge customer
     *
     * @param customer Customer
     * @param request CustomerRequest
     */
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
