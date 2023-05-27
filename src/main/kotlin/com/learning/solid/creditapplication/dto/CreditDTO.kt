package com.learning.solid.creditapplication.dto

import com.learning.solid.creditapplication.entity.Credit
import com.learning.solid.creditapplication.entity.Customer
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDTO(

  @field:NotNull(message = "this field can not be empty")
  val creditValue: BigDecimal,

  @field:Future(message = "this field can not be empty")
  val dayFirstOfInstallment: LocalDate,

  val numberOfInstallment: Int,

  @field:NotNull(message = "this field can not be empty")
  val customerId: Long
) {
  fun toEntity(): Credit = Credit(
    creditValue = this.creditValue,
    dayFirstInstallment = this.dayFirstOfInstallment,
    numberOfInstallments = this.numberOfInstallment,
    customer = Customer(
      id = this.customerId
    )
  )
}
