package com.learning.solid.creditapplication.entity

data class Customer (
    val id: Long = 0,
    var firstName: String = "",
    var lastName: String = "",
    val cpf: String = "",
    var email: String = "",
    var password: String = "",
    var address: Address = Address(),
    var credits: List<Credit>
)