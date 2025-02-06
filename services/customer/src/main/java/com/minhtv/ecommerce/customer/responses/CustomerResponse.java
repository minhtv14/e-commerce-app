package com.minhtv.ecommerce.customer.responses;

import com.minhtv.ecommerce.customer.entities.Address;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
