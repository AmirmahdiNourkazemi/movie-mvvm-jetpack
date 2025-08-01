package com.example.movie.utils

import com.example.movie.domain.ApiInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    val api : ApiInterface by lazy {
        Retrofit.Builder().baseUrl(Util.Base).addConverterFactory(GsonConverterFactory.create()).build().create(ApiInterface::class.java)
    }
}