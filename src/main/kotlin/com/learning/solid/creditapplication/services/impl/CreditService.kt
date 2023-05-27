package com.learning.solid.creditapplication.services.impl

import com.learning.solid.creditapplication.entity.Credit
import com.learning.solid.creditapplication.exception.BusinessException
import com.learning.solid.creditapplication.repository.CreditRepository
import com.learning.solid.creditapplication.services.ICreditService
import org.springframework.stereotype.Service
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
): ICreditService {
    override fun save(credit: Credit): Credit {
        credit.apply {
            customer = customerService.findById(credit.customer?.id!!)
        }

        return this.creditRepository.save(credit)
    }

    override fun findAllByCostumer(costumerId: Long): List<Credit> =
        this.creditRepository.findAllByCustomerId(costumerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit = this.creditRepository.findByCreditCode(creditCode)
            ?: throw BusinessException("credit $creditCode doesn't exist")

        return if(credit.customer?.id == customerId) credit else throw IllegalAccessException("you must to talk to admin")
    }
}