package com.learning.solid.creditapplication.controller

import com.learning.solid.creditapplication.dto.CreditDTO
import com.learning.solid.creditapplication.dto.CreditView
import com.learning.solid.creditapplication.dto.CreditViewList
import com.learning.solid.creditapplication.entity.Credit
import com.learning.solid.creditapplication.repository.CreditRepository
import com.learning.solid.creditapplication.services.impl.CreditService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditController(private val creditService: CreditService) {

  @PostMapping
  fun saveCredit(@RequestBody creditDTO: CreditDTO): String {
    val credit: Credit = this.creditService.save(creditDTO.toEntity())
    return "credit ${credit.creditCode} - customer ${credit.customer?.firstName} saved"
  }

  @GetMapping
  fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): List<CreditViewList> {
    return this.creditService.findAllByCostumer(customerId).stream().map { credit: Credit ->
      CreditViewList(credit)
    }.collect(Collectors.toList())
  }

  @GetMapping("/{creditId}")
  fun findByCreditCode(
    @RequestParam(value = "customerId") customerId: Long,
    @PathVariable creditCode: UUID
  ): CreditView {
    val credit: Credit = this.creditService.findByCreditCode(customerId, creditCode)
    return CreditView(credit)
  }
}