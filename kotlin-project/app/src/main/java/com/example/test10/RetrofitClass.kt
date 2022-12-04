package com.example.test10

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClass {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://172.30.40.243:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val _api = retrofit.create(RetrofitInterface::class.java)
    val api get() = _api
}