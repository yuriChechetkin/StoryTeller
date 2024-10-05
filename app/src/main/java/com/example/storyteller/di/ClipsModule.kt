package com.example.storyteller.di

import com.example.storyteller.BuildConfig
import com.example.storyteller.data.AuthInterceptor
import com.example.storyteller.data.ClipsService
import com.example.storyteller.domain.providers.ShareProvider
import com.example.storyteller.domain.providers.ShareProviderImpl
import com.example.storyteller.domain.repositories.ClipsRepository
import com.example.storyteller.domain.repositories.ClipsRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ClipsModule {

    @Binds
    fun bindClipsRepository(impl: ClipsRepositoryImpl): ClipsRepository

    @Binds
    fun bindShareProvider(impl: ShareProviderImpl): ShareProvider

    companion object {

        @Provides
        fun provideBaseUrl(): String {
            return BuildConfig.API_BASE_URL
        }

        @Provides
        fun provideGson(): Gson {
            return GsonBuilder()
                .create()
        }

        @Provides
        fun provideOkHttpClient(): OkHttpClient {
            return OkHttpClient.Builder()
                .addInterceptor(
                    provideAuthInterceptor()
                )
                .build()
        }

        @Provides
        fun provideAuthInterceptor(): AuthInterceptor {
            return AuthInterceptor(BuildConfig.API_KEY)
        }

        @Provides
        fun provideRetrofit(
            baseUrl: String,
            gson: Gson,
            okHttpClient: OkHttpClient
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        @Provides
        @Singleton
        fun provideApiService(retrofit: Retrofit): ClipsService =
            retrofit.create(ClipsService::class.java)

    }

}