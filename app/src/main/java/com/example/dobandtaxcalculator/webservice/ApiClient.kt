package com.example.dobandtaxcalculator.webservice

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

const val BASE_URL = "https://picsum.photos/"

object ApiClient {
    fun create(): ApiStories {
        val retrofit = Retrofit.Builder()
            .client(getOkClient().build())
            .baseUrl(BASE_URL)
            .build()

        return retrofit.create(ApiStories::class.java)
    }

   private fun getOkClient() : OkHttpClient.Builder{
       val logging = HttpLoggingInterceptor()
       logging.level = (HttpLoggingInterceptor.Level.BASIC)
        val okHttpClient = OkHttpClient.Builder()
       okHttpClient.addInterceptor(logging)
        return okHttpClient
    }

}
