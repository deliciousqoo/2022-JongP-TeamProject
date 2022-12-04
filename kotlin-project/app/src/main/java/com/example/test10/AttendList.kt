package com.example.test10

import com.google.gson.annotations.SerializedName

data class AttendList (
    @SerializedName("list")
    val list: MutableList<AttendItem>
    )

data class AttendItem (
    val SSN: String,
    val name: String,
    val rank: String,
    val attendance: Boolean
    )