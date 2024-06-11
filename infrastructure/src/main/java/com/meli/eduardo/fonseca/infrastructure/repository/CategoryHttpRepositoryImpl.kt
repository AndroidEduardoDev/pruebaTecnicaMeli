package com.meli.eduardo.fonseca.infrastructure.repository

import com.meli.eduardo.fonseca.domain.entity.Category
import com.meli.eduardo.fonseca.domain.exception.Exceptions
import com.meli.eduardo.fonseca.domain.repository.Repository
import com.meli.eduardo.fonseca.infrastructure.datasource.remote.CategoryApi
import com.meli.eduardo.fonseca.infrastructure.mapper.toDomain
import kotlinx.coroutines.delay
import javax.inject.Inject

class CategoryHttpRepositoryImpl @Inject constructor(private val apiService: CategoryApi) : Repository<Category>{
    override suspend fun getAll(parameters: String?): List<Category> {
       try {
           return apiService.getAllCategories().toDomain()
       }catch (e:Exception){
           throw  e
       }
    }
}


