package com.learning.solid.creditapplication.dto

import com.learning.solid.creditapplication.entity.Credit
import com.learning.solid.creditapplication.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDTO(
  val creditValue: BigDecimal,
  val dayFirstOfInstallment: LocalDate,
  val numberOfInstallment: Int,
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
