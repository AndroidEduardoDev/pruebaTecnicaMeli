package com.meli.eduardo.fonseca.infrastructure.mapper

import com.meli.eduardo.fonseca.domain.entity.Category
import com.meli.eduardo.fonseca.domain.exception.Exceptions.Companion.EXCEPTION_CONVERT_CATEGORY
import com.meli.eduardo.fonseca.infrastructure.entity.CategoryRemoteResponseElement

fun CategoryRemoteResponseElement.toDomain(): Category {
    try {
        return Category(id = this.id, name = this.name)
    } catch (e: Exception) {
        throw EXCEPTION_CONVERT_CATEGORY
    }
}

fun List<CategoryRemoteResponseElement>.toDomain(): List<Category> {
    try {
        return this.map {
            it.toDomain()
        }
    } catch (e: Exception) {
        throw EXCEPTION_CONVERT_CATEGORY
    }
}