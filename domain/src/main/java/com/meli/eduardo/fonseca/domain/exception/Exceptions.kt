package com.meli.eduardo.fonseca.domain.exception

class Exceptions {
    companion object{
        val EXCEPTION_CONVERT_PRODUCT  = RuntimeException("Exception convert product")
        val EXCEPTION_CONVERT_CATEGORY = RuntimeException("Exception convert category")
        val EXCEPTION_ERROR_CONSUME_SERVICE = RuntimeException("Exception when intent consume service")
    }
}