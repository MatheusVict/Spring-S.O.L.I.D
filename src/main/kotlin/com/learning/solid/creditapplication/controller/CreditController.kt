package com.learning.solid.creditapplication.controller

import com.learning.solid.creditapplication.dto.CreditDTO
import com.learning.solid.creditapplication.dto.CreditView
import com.learning.solid.creditapplication.dto.CreditViewList
import com.learning.solid.creditapplication.entity.Credit
import com.learning.solid.creditapplication.repository.CreditRepository
import com.learning.solid.creditapplication.services.impl.CreditService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
  fun saveCredit(@RequestBody @Valid creditDTO: CreditDTO): ResponseEntity<String> {
    val credit: Credit = this.creditService.save(creditDTO.toEntity())
    return ResponseEntity.status(HttpStatus.CREATED)
      .body("credit ${credit.creditCode} - customer ${credit.customer?.firstName} saved")
  }

  @GetMapping
  fun findAllByCustomerId(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<CreditViewList>> {
    val creditViewList: List<CreditViewList> =
      this.creditService.findAllByCostumer(customerId).stream().map { credit: Credit ->
        CreditViewList(credit)
      }.collect(Collectors.toList())
    return ResponseEntity.ok().body(creditViewList)
  }

  @GetMapping("/{creditCode}")
  fun findByCreditCode(
    @RequestParam(value = "customerId") customerId: Long,
    @PathVariable creditCode: UUID
  ): ResponseEntity<CreditView> {
    val credit: Credit = this.creditService.findByCreditCode(customerId, creditCode)
    return ResponseEntity.ok().body(CreditView(credit))
  }
}