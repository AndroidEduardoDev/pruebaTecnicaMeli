package com.meli.eduardo.fonseca.domain.usecase

import com.meli.eduardo.fonseca.domain.entity.Category
import com.meli.eduardo.fonseca.domain.repository.Repository
import kotlinx.coroutines.delay
import javax.inject.Inject

class GetCategoryUseCase @Inject constructor(private val repository: Repository<Category>) {
    suspend operator fun invoke(): Result<List<Category>> {
        return try {
            Result.Loading
            val products = repository.getAll(null)
            Result.Success(products)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}