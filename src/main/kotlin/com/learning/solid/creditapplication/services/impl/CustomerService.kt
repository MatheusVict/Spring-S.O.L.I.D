package com.learning.solid.creditapplication.services.impl

import com.learning.solid.creditapplication.entity.Customer
import com.learning.solid.creditapplication.repository.CustomerRepository
import com.learning.solid.creditapplication.services.ICostumerService
import org.springframework.stereotype.Service
import java.lang.RuntimeException

@Service
class CustomerService(private val customerRepository: CustomerRepository): ICostumerService {
    override fun save(customer: Customer): Customer =
     this.customerRepository.save(customer)

    override fun findById(customerId: Long): Customer =
        this.customerRepository.findById(customerId).orElseThrow {
            throw RuntimeException("id $customerId not found")
        }

    override fun delete(customerId: Long) {
        this.customerRepository.deleteById(customerId)
    }

}