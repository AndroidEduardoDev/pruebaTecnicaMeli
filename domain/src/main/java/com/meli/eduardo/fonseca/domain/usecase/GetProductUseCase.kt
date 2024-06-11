package com.meli.eduardo.fonseca.domain.usecase

import com.meli.eduardo.fonseca.domain.entity.Product
import com.meli.eduardo.fonseca.domain.exception.Exceptions.Companion.EXCEPTION_CONVERT_PRODUCT
import com.meli.eduardo.fonseca.domain.exception.Exceptions.Companion.EXCEPTION_ERROR_CONSUME_SERVICE
import com.meli.eduardo.fonseca.domain.repository.Repository
import javax.inject.Inject

class GetProductUseCase @Inject constructor(private val repository: Repository<Product>) {
    suspend operator fun invoke(p: String?): Result<List<Product>> {
        return try {
            Result.Loading
            val products = repository.getAll(p)
            Result.Success(products)
        } catch (e: Exception) {
            Result.Error(EXCEPTION_ERROR_CONSUME_SERVICE)
        }
    }
}