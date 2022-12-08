package com.example.test10

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {
    @GET("JongP_Android_DB/atte.jsp")
    fun getItems(@Query("EventNo") EventNo:Int): Call<AttendList>

    @GET("JongP_Android_DB/codeCheck.jsp")
    fun getCodeCheck(@Query("Ssn") Ssn:String,
                     @Query("Code") Code:String,
                     @Query("EventNo") EventNo:Int): Call<checkBooleanClass>

    @GET("JongP_Android_DB/showEvent.jsp")
    fun getEventInfo(@Query("EventNo") EventNo:Int): Call<EventInfo>

    @GET("JongP_Android_DB/manageAttend.jsp")
    fun getChangeStatus(@Query("Ssn") Ssn:String,
                        @Query("Attend") Attend:Boolean,
                        @Query("EventNo") EventNo:Int): Call<checkBooleanClass>

    @GET("JongP_Android_DB/checkAttend.jsp")
    fun getCheckStatus(@Query("Ssn") Ssn:String,
                        @Query("EventNo") EventNo:Int): Call<checkBooleanClass>

    @GET("JongP_Android_DB/getVoteList.jsp")
    fun getVoteItems(@Query("EventNo") EventNo:Int): Call<VoteList>
}