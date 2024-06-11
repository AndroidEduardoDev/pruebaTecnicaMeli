package com.meli.eduardo.fonseca.infrastructure.repository
import com.meli.eduardo.fonseca.infrastructure.datasource.remote.CategoryApi
import com.meli.eduardo.fonseca.infrastructure.entity.CategoryRemoteResponseElement
import com.meli.eduardo.fonseca.infrastructure.mapper.toDomain
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CategoryHttpRepositoryImplTest {

    @Mock
    private lateinit var mockApiService: CategoryApi

    private lateinit var repository: CategoryHttpRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = CategoryHttpRepositoryImpl(mockApiService)
    }

    @Test
    fun `getAll should return list of categories from api service`() = runBlocking {
        val mockApiResponse = listOf(
            CategoryRemoteResponseElement(id ="1", name = "Category 1"),
            CategoryRemoteResponseElement(id = "2", name = "Category 2")
        )
        val expectedDomainList = mockApiResponse.map { it.toDomain() }
        `when`(mockApiService.getAllCategories()).thenReturn(mockApiResponse)
        val result = repository.getAll(null)
        assertEquals(expectedDomainList, result)
    }


}