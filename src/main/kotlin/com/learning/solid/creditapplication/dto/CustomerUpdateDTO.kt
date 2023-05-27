package com.learning.solid.creditapplication.dto

import com.learning.solid.creditapplication.entity.Customer
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class CustomerUpdateDTO(
  @field:NotEmpty(message = "this field can not be empty")
  val firstName: String,

  @field:NotEmpty(message = "this field can not be empty")
  val lastName: String,

  @field:NotNull(message = "this field can not be empty")
  val income: BigDecimal,

  @field:NotEmpty(message = "this field can not be empty")
  val zipCode: String,

  @field:NotEmpty(message = "this field can not be empty")
  val street: String
) {
  fun toEntity(customer: Customer): Customer {
    customer.firstName = this.firstName
    customer.lastName = this.lastName
    customer.income = this.income
    customer.address.street
    return customer
  }
}
