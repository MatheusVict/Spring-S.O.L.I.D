package com.learning.solid.creditapplication.controller

import com.learning.solid.creditapplication.dto.CustomerDTO
import com.learning.solid.creditapplication.dto.CustomerUpdateDTO
import com.learning.solid.creditapplication.dto.CustomerView
import com.learning.solid.creditapplication.entity.Customer
import com.learning.solid.creditapplication.services.impl.CustomerService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customers")
class CustomerController(
  private val customerService: CustomerService
) {

  @PostMapping
  fun saveCustomer(@RequestBody customerDTO: CustomerDTO): String {
    val savedCustomer = this.customerService.save(customerDTO.toEntity())
    return "saved Customer ${savedCustomer.email} saved"
  }

  @GetMapping("/{customerId}")
  fun findById(@PathVariable customerId: Long): CustomerView {
    val customer: Customer = this.customerService.findById(customerId)
    return CustomerView(customer)
  }

  @PatchMapping("/{customerId}")
  fun update(
    @RequestParam(value = "customerId") customerId: Long,
    @RequestBody customerUpdateDTO: CustomerUpdateDTO
  ): CustomerView {
    val customer: Customer = this.customerService.findById(customerId)
    val customerToUpdate = customerUpdateDTO.toEntity(customer)
    val customerUpdated: Customer = this.customerService.save(customerToUpdate)
    return CustomerView(customerUpdated)

  }

  @DeleteMapping("/{customerId}")
  fun delete(@PathVariable customerId: Long) =
    this.customerService.delete(customerId)

}