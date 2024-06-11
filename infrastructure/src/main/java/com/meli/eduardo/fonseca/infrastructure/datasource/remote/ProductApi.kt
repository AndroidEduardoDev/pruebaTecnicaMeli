package com.meli.eduardo.fonseca.infrastructure.datasource.remote

import com.meli.eduardo.fonseca.infrastructure.entity.ProductRemoteResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductApi  {
    @GET("sites/MLA/search")
    suspend fun getAllProductByCategory(@Query("q") categoryId: String?): ProductRemoteResponse?

}