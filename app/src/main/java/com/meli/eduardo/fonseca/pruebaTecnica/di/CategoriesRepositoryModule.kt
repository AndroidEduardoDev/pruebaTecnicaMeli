package com.meli.eduardo.fonseca.pruebaTecnica.di

import com.meli.eduardo.fonseca.domain.entity.Category
import com.meli.eduardo.fonseca.domain.repository.Repository
import com.meli.eduardo.fonseca.infrastructure.BuildConfig
import com.meli.eduardo.fonseca.infrastructure.datasource.remote.CategoryApi
import com.meli.eduardo.fonseca.infrastructure.repository.CategoryHttpRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object CategoriesRepositoryModule {

    @Provides
    fun provideCategoryRepository(): Repository<Category> {
        return CategoryHttpRepositoryImpl(provideCategoryApi())
    }

    @Provides
    fun provideCategoryApi(): CategoryApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(CategoryApi::class.java)
    }
}
