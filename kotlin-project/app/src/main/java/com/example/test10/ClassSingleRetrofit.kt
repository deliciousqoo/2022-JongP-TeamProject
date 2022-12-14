package com.example.test10

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClassSingleRetrofit {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.45.54:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val _api = retrofit.create(InterfaceRetrofit::class.java)
    val api get() = _api
}