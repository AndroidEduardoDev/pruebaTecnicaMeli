package com.meli.eduardo.fonseca.domain.usecase

import com.meli.eduardo.fonseca.domain.entity.Category
import com.meli.eduardo.fonseca.domain.exception.Exceptions.Companion.EXCEPTION_CONVERT_CATEGORY
import com.meli.eduardo.fonseca.domain.repository.Repository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCategoryUseCaseTest {

    @Mock
    private lateinit var mockRepository: Repository<Category>

    @Test
    fun `invoke should return Success when repository returns categories`() = runBlocking {
        val categories = listOf(
            Category(id = "1", name = "Category 1"),
            Category(id = "2", name = "Category 2")
        )
        `when`(mockRepository.getAll(null)).thenReturn(categories)
        val getCategoryUseCase = GetCategoryUseCase(mockRepository)
        val result = getCategoryUseCase.invoke()
        assertEquals(Result.Success(categories), result)
    }

    @Test
    fun `invoke should return Error when repository throws exception`() = runBlocking {
        `when`(mockRepository.getAll(null)).thenThrow(RuntimeException("Repository error"))
        val getCategoryUseCase = GetCategoryUseCase(mockRepository)
        val result = getCategoryUseCase.invoke()
        assertEquals(Result.Error(EXCEPTION_CONVERT_CATEGORY), result)
    }
}