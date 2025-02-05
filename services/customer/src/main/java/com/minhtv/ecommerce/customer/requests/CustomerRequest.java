package com.minhtv.ecommerce.customer.requests;

import com.minhtv.ecommerce.customer.entities.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        String id,
        @NotNull(message = "Customer firstname is requited")
        String firstName,
        @NotNull(message = "Customer lastname is requited")
        String lastName,
        @NotNull(message = "Customer email is requited")
        @Email(message = "Customer email is not a valid email address")
        String email,
        Address address
) {

}
