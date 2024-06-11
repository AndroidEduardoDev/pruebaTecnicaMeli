package com.meli.eduardo.fonseca.pruebaTecnica.di

import com.meli.eduardo.fonseca.domain.entity.Product
import com.meli.eduardo.fonseca.domain.repository.Repository
import com.meli.eduardo.fonseca.infrastructure.BuildConfig
import com.meli.eduardo.fonseca.infrastructure.datasource.remote.CategoryApi
import com.meli.eduardo.fonseca.infrastructure.datasource.remote.ProductApi
import com.meli.eduardo.fonseca.infrastructure.repository.ProductHttpRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ProductsRepositoryModule {

    @Provides
    fun provideProductRepository(): Repository<Product> {
        return ProductHttpRepositoryImpl(provideProductsApi())
    }

    @Provides
    fun provideProductsApi(): ProductApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ProductApi::class.java)
    }
}

