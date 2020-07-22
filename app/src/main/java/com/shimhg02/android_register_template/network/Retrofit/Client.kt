package com.shimhg02.android_register_template.network.Retrofit


import com.shimhg02.android_register_template.network.Api.API
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @description Retrofit 클라이언트
 */

object Client {
    var retrofitService: API
    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val logger = OkHttpClient.Builder().addInterceptor(interceptor).readTimeout(20, TimeUnit.SECONDS).writeTimeout(20, TimeUnit.SECONDS).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jjan.andy0414.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(logger)
            .build()
        retrofitService = retrofit.create(API::class.java)
    }
}