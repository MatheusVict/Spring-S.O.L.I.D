package com.learning.solid.creditapplication.exception

import org.springframework.dao.DataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDate

@RestControllerAdvice
class RestExceptionHandler {
  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun handlerValidException(exception: MethodArgumentNotValidException): ResponseEntity<ExceptionDetails> {
    val errors: MutableMap<String, String?> = HashMap()
    exception.bindingResult.allErrors.stream().forEach{
      error: ObjectError ->
      val fieldName: String = (error as FieldError).field
      val messageError: String? = error.defaultMessage
      errors[fieldName] = messageError
    }

    return ResponseEntity(
      ExceptionDetails(
        title = "Bad Request, Consult the documentation",
        timestamp = LocalDate.now(),
        status = HttpStatus.BAD_REQUEST.value(),
        exception = exception.javaClass.toString(),
        details = errors
      ),
      HttpStatus.BAD_REQUEST
    )
  }

  @ExceptionHandler(DataAccessException::class)
  fun handlerValidException(exception: DataAccessException): ResponseEntity<ExceptionDetails> {
    return ResponseEntity.status(HttpStatus.CONFLICT)
      .body(
      ExceptionDetails(
        title = "Conflict, Consult the documentation",
        timestamp = LocalDate.now(),
        status = HttpStatus.CONFLICT.value(),
        exception = exception.javaClass.toString(),
        details = mutableMapOf(exception.cause.toString() to exception.message)
      )
    )
  }
}