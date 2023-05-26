package com.learning.solid.creditapplication.dto

import com.learning.solid.creditapplication.entity.Customer
import java.math.BigDecimal

data class CustomerUpdateDTO (
  val firstName: String,
  val lastName: String,
  val income: BigDecimal,
  val zipCode: String,
  val street: String
){
  fun toEntity(customer: Customer): Customer {
    customer.firstName = this.firstName
    customer.lastName = this.lastName
    customer.income = this.income
    customer.address.street
    return customer
  }
}
