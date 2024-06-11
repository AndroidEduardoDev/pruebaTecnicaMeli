package com.meli.eduardo.fonseca.domain.repository

interface Repository<T> {
    suspend fun getAll(parameters: String?): List<T>
}