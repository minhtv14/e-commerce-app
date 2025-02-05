package com.minhtv.ecommerce.customer.repositories;

import com.minhtv.ecommerce.customer.entities.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {

}
