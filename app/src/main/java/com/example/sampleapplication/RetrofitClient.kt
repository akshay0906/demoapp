package com.example.sampleapplication

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitClient {

    fun getClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.exchangeratesapi.io/")
            .client(getokHttp())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getokHttp(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }
        return httpClient.build()
    }

    fun getApiInterface(): ApiInterface {
        return getClient().create(ApiInterface::class.java)
    }

}