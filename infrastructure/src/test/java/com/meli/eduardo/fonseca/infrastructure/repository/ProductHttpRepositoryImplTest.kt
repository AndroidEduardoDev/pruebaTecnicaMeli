package com.meli.eduardo.fonseca.infrastructure.repository
import com.meli.eduardo.fonseca.domain.exception.Exceptions
import com.meli.eduardo.fonseca.infrastructure.datasource.remote.ProductApi
import com.meli.eduardo.fonseca.infrastructure.entity.CategoryRemoteResponseElement
import com.meli.eduardo.fonseca.infrastructure.entity.ProductRemoteResponse
import com.meli.eduardo.fonseca.infrastructure.mapper.toDomain
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class ProductHttpRepositoryImplTest {

    @Mock
    private lateinit var mockApiService: ProductApi

    private lateinit var repository: ProductHttpRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = ProductHttpRepositoryImpl(mockApiService)
    }

    @Test
    fun `getAll should return list of categories from api service`() = runBlocking {
        // Mock data
        val mockApiResponse = listOf(
            ProductRemoteResponse.Result(id ="1", title = "Category 1", categoryId = "", thumbnail = "", price = 100.0),
        )
        val expectedDomainList = ProductRemoteResponse(mockApiResponse)
        `when`(mockApiService.getAllProductByCategory("")).thenReturn(expectedDomainList)
        val result = repository.getAll("")
        assertEquals(expectedDomainList.results.toDomain(), result)
    }

    @Test
    fun `getAll should throw specific exception when api service fails`() = runBlocking {
        `when`(mockApiService.getAllProductByCategory("")).thenThrow(Exceptions.EXCEPTION_ERROR_CONSUME_SERVICE)

        try {
            repository.getAll("")
            assert(false) { "Expected exception not thrown" }
        } catch (e: Exception) {
            assertEquals(Exceptions.EXCEPTION_ERROR_CONSUME_SERVICE, e)
        }
    }

}