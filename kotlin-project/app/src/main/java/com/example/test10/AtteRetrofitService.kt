package com.example.test10

import retrofit2.Call
import retrofit2.http.GET

interface AtteRetrofitService {
    @GET("JongP_Android_DB/atte.jsp")
    fun getAttendList():Call<AttendList>
}