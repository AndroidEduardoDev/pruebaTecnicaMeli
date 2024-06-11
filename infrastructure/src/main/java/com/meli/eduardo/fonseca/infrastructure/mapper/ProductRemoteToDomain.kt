package com.meli.eduardo.fonseca.infrastructure.mapper

import com.meli.eduardo.fonseca.domain.entity.Product
import com.meli.eduardo.fonseca.domain.exception.Exceptions
import com.meli.eduardo.fonseca.infrastructure.entity.ProductRemoteResponse


fun List<ProductRemoteResponse.Result>.toDomain(): List<Product> {
    try {
        return this.map {
             Product(id = it.id, name = it.title, price = it.price, image = it.thumbnail.replace("http:", "https:").replace("I.jpg", "O.webp"))
        }
    } catch (e: Exception) {
        throw Exceptions.EXCEPTION_CONVERT_PRODUCT
    }
}