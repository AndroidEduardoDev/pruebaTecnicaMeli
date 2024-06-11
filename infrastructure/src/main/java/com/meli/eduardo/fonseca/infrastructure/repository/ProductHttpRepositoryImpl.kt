package com.meli.eduardo.fonseca.infrastructure.repository

import com.meli.eduardo.fonseca.domain.entity.Product
import com.meli.eduardo.fonseca.domain.exception.Exceptions
import com.meli.eduardo.fonseca.domain.repository.Repository
import com.meli.eduardo.fonseca.infrastructure.datasource.remote.ProductApi
import com.meli.eduardo.fonseca.infrastructure.mapper.toDomain
import javax.inject.Inject

class ProductHttpRepositoryImpl @Inject constructor(private val apiService: ProductApi) :
    Repository<Product> {
    override suspend fun getAll(parameters: String?): List<Product> {
        try {
            return apiService.getAllProductByCategory(parameters)?.results?.toDomain()!!
        }catch (e:Exception){
            throw  e
        }
    }

}


