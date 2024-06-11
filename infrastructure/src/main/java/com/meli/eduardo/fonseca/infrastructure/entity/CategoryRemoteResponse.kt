package com.meli.eduardo.fonseca.infrastructure.entity

import androidx.annotation.Keep
import kotlinx.serialization.SerialName


typealias CategoryRemoteResponse = List<CategoryRemoteResponseElement>

@Keep
data class CategoryRemoteResponseElement (
    val id: String,
    val name: String
)
