package com.example.dobandtaxcalculator.webservice

import okhttp3.ResponseBody
import retrofit2.http.GET

interface ApiStories {
    @GET("200")
    suspend fun downloadImage() : ResponseBody
}