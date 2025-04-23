package com.example.unlocktechtask.server.apiservice


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    private const val BASE_URL = "https://uat-mb2bv1.paypointindia.co.in/"

    val api: ApiService by lazy {
        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("accept-encoding", "gzip")
                    .addHeader("connection", "Keep-Alive")
                    .addHeader("content-type", "application/json; charset=UTF-8")
                    .addHeader("host", "uat-mb2bv1.paypointindia.co.in")
                    .addHeader("user-agent", "okhttp/3.14.9")
                    .addHeader("x-api-key", "Basic TVNFcGpXa1BMMTptVGFpZm1xV3paZDR4")
                    .build()
                chain.proceed(request)
            }.build()

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
