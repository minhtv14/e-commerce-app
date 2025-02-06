package com.minhtv.ecommerce.customer.controller;

import com.minhtv.ecommerce.customer.requests.CustomerRequest;
import com.minhtv.ecommerce.customer.responses.CustomerResponse;
import com.minhtv.ecommerce.customer.services.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Customer controller
 */
@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    /**
     *  Create a new customer
     *
     * @param request CustomerRequest
     * @return ResponseEntity<String>
     */
    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest request
    ) {
        return ResponseEntity.ok(service.createCustomer(request));
    }

    /**
     *  Update a customer
     *
     *  @param request CustomerRequest
     *  @return ResponseEntity<Void>
     */
    @PutMapping
    public ResponseEntity<Void> updateCustomer(
            @RequestBody @Valid CustomerRequest request
    ) {
        service.updateCustomer(request);
        return ResponseEntity.accepted().build();
    }

    /**
     *  Get customers by condition
     *
     *  @return ResponseEntity<List<CustomerResponse>>
     */
    @GetMapping
    public ResponseEntity<List<CustomerResponse>> getCustomersByCondition() {
        return ResponseEntity.ok(service.getCustomersByCondition());
    }

    /**
     *  Check if customer exists by id
     *
     *  @param customerId String
     *  @return ResponseEntity<Boolean>
     */
    @GetMapping("/exits/{customer-id}")
    public ResponseEntity<Boolean> exitsById(
            @PathVariable("customer-id") String customerId
    ) {
        return ResponseEntity.ok(service.exitsById(customerId));
    }

    /**
     *  Get customer by id
     *
     *  @param customerId String
     *  @return ResponseEntity<CustomerResponse>
     */
    @GetMapping("/{customer-id}")
    public ResponseEntity<CustomerResponse> getCustomerById(
            @PathVariable("customer-id") String customerId
    ) {
        return ResponseEntity.ok(service.getCustomerById(customerId));
    }

    /**
     *  Delete customer by id
     *
     *  @param customerId String
     *  @return ResponseEntity<Void>
     */
    @DeleteMapping("/{customer-id}")
    public ResponseEntity<Void> deleteCustomerById(
            @PathVariable("customer-id") String customerId
    ) {
        service.deleteCustomerById(customerId);
        return ResponseEntity.noContent().build();
    }

}
