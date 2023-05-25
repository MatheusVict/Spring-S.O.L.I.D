package com.learning.solid.creditapplication.services

import com.learning.solid.creditapplication.entity.Customer

interface ICostumerService {

    fun save(customer: Customer): Customer
    fun findById(customerId: Long): Customer
    fun delete(customerId: Long)
}