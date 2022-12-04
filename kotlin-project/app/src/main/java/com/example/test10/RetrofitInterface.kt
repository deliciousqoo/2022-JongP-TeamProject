package com.example.test10

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("JongP_Android_DB/atte.jsp")
    fun getItems(@Query("EventNo") EventNo:Int): Call<AttendList>

    @GET("JongP_Android_DB/atte.jsp")
    fun getItemsas(@Query("EventNo") EventNo:Int): Call<AttendList>
}