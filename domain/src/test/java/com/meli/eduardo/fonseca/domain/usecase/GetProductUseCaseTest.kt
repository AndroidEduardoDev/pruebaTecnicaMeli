package com.meli.eduardo.fonseca.domain.usecase

import com.meli.eduardo.fonseca.domain.entity.Product
import com.meli.eduardo.fonseca.domain.exception.Exceptions
import com.meli.eduardo.fonseca.domain.repository.Repository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetProductUseCaseTest {

    @Mock
    private lateinit var mockRepository: Repository<Product>

    @Test
    fun `invoke should return Success when repository returns products`() = runBlocking {
        val products = listOf(
            Product(id = "1", name = "p 1", image = "https//imagen.com", price = 10.00),
            Product(id = "2", name = "p 2", image = "https//imagen.com", price = 10.00)
        )
        Mockito.`when`(mockRepository.getAll("")).thenReturn(products)
        val getProductUseCase = GetProductUseCase(mockRepository)
        val result = getProductUseCase.invoke("")
        assertEquals(Result.Success(products), result)
    }

    @Test
    fun `invoke should return Error when repository throws exception`() = runBlocking {
        Mockito.`when`(mockRepository.getAll(null)).thenThrow(RuntimeException("Repository error"))
        val getProductUseCase = GetProductUseCase(mockRepository)
        val result = getProductUseCase.invoke(null)
        assertEquals(Result.Error(Exceptions.EXCEPTION_CONVERT_PRODUCT), result)
    }
}