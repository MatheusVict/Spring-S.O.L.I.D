package com.learning.solid.creditapplication.dto

import com.learning.solid.creditapplication.entity.Address
import com.learning.solid.creditapplication.entity.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDTO(
    @field:NotEmpty(message = "this field can not be empty")
    val firstName: String,

    @field:NotEmpty(message = "this field can not be empty")
    val lastName: String,

    @field:NotEmpty(message = "this field can not be empty")
    @field:CPF(message = "This is invalid cpf")
    val cpf: String,

    @field:NotNull(message = "this field can not be empty")
    val income: BigDecimal,

    @field:NotEmpty(message = "this field can not be empty")
    @field:Email(message = "this is not a valid email")
    val email: String,

    @field:NotEmpty(message = "this field can not be empty")
    val password: String,

    @field:NotEmpty(message = "this field can not be empty")
    val zipCode: String,

    @field:NotEmpty(message = "this field can not be empty")
    val street: String
) {
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )
    )
}
