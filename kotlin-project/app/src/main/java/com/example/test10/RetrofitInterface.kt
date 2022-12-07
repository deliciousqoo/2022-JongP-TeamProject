package com.example.test10

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("atte.jsp")
    fun getItems(@Query("EventNo") EventNo:Int): Call<AttendList>

    @GET("showEvent.jsp")
    fun getEventInfo(@Query("EventNo") EventNo:Int): Call<EventInfo>
}