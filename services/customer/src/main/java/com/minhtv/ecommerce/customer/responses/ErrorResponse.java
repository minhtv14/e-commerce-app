package com.minhtv.ecommerce.customer.responses;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}