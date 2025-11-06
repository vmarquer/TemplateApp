package com.example.templateapp.data.remote

import android.content.Context
import com.example.templateapp.data.preferences.UserPreferences
import com.example.templateapp.data.remote.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CitationModule {

    private const val BASE_URL = "https://url/"

    class AuthInterceptor(private val userPreferences: UserPreferences) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val token = runBlocking { userPreferences.bearerToken.first() }
            val request = chain.request().newBuilder()
            token?.let {
                request.addHeader("Authorization", "Bearer $it")
            }
            return chain.proceed(request.build())
        }
    }

    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferences(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(userPreferences: UserPreferences): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(userPreferences))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
