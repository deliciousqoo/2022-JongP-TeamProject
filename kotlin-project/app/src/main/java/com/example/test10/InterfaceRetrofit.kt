package com.example.test10

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface InterfaceRetrofit {
    @GET("JongP_Android_DB/atte.jsp")
    fun getItems(@Query("EventNo") EventNo:Int): Call<AttendList>

    @GET("JongP_Android_DB/checkAttend.jsp")
    fun getCheckStatus(@Query("Ssn") Ssn:String,
                       @Query("EventNo") EventNo:Int): Call<checkBooleanClass>

    @GET("JongP_Android_DB/codeCheck.jsp")
    fun getCodeCheck(@Query("Ssn") Ssn:String,
                     @Query("Code") Code:String,
                     @Query("EventNo") EventNo:Int): Call<checkBooleanClass>

    @GET("JongP_Android_DB/getVoteInfo.jsp")
    fun getVoteInfo(@Query("Ssn") Ssn:String,
                    @Query("EventNo") EventNo: Int,
                    @Query("VoteNo") VoteNo:Int): Call<VoteInfo>

    @GET("JongP_Android_DB/getVoteList.jsp")
    fun getVoteItems(@Query("EventNo") EventNo:Int): Call<VoteList>

    @GET("JongP_Android_DB/insertVote.jsp")
    fun insertVote(@Query("EventNo") EventNo: Int,
                    @Query("Agenda") Agenda: String,
                    @Query("Content") Content: String) : Call<checkBooleanClass>

    @GET("JongP_Android_DB/manageAttend.jsp")
    fun getChangeStatus(@Query("Ssn") Ssn:String,
                        @Query("Attend") Attend:Boolean,
                        @Query("EventNo") EventNo:Int): Call<checkBooleanClass>

    @GET("JongP_Android_DB/participateVote.jsp")
    fun participateVote(@Query("Ssn") Ssn:String,
                        @Query("EventNo") EventNo: Int,
                        @Query("VoteNo") VoteNo:Int,
                        @Query("Answer") Answer: Int): Call<checkBooleanClass>

    @GET("JongP_Android_DB/showEvent.jsp")
    fun getEventInfo(@Query("EventNo") EventNo:Int): Call<EventInfo>

    @GET("JongP_Android_DB/updateTime.jsp")
    fun changeVoteStatus(@Query("EventNo") EventNo:Int,
                         @Query("VoteNo") VoteNo:Int,
                         @Query("isStart") isStart:Boolean ):Call<checkBooleanNTimeClass>

}