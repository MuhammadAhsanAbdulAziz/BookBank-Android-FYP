package com.example.bookbank.di

import com.example.bookbank.api.AuthApi
import com.example.bookbank.api.BookApi
import com.example.bookbank.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(authInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideAuthApi(retrofitBuilder: Retrofit.Builder): AuthApi {
        return retrofitBuilder
            .build()
            .create(AuthApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBooksApi(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient): BookApi {
        return retrofitBuilder
            .client(okHttpClient)
            .build()
            .create(BookApi::class.java)
    }
}