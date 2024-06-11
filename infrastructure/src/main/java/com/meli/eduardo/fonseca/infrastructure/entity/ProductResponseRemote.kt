package com.meli.eduardo.fonseca.infrastructure.entity

import androidx.annotation.Keep
import kotlinx.serialization.SerialName

@Keep
data class ProductRemoteResponse (
    val results: List<Result>
){
    @Keep
    data class Result (
        val id: String,
        val title: String,
        @SerialName("category_id")
        val categoryId: String,
        val thumbnail: String,
        val price: Double,
    )

}

