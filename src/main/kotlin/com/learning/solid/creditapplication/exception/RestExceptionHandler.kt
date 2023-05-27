package com.learning.solid.creditapplication.exception

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
}