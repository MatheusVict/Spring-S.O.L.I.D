package com.learning.solid.creditapplication.exception

data class BusinessException(override val message: String?) : RuntimeException(message)