package com.meli.eduardo.fonseca.infrastructure.datasource.remote

import com.meli.eduardo.fonseca.infrastructure.entity.CategoryRemoteResponse
import retrofit2.http.GET

interface CategoryApi  {
    @GET("sites/MLA/categories")
    suspend fun getAllCategories(): CategoryRemoteResponse

}