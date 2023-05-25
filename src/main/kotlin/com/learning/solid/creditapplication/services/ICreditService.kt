package com.learning.solid.creditapplication.services

import com.learning.solid.creditapplication.entity.Credit
import org.aspectj.apache.bcel.classfile.Code
import java.util.UUID

interface ICreditService {

    fun save(credit: Credit): Credit
    fun findAllByCostumer(costumerId: Long): List<Credit>
    fun findByCreditCode(customerId: Long, creditCode: UUID): Credit
}