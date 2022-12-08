package com.example.test10

data class EventInfo (
    val NAME: String,
    val LOCATION: String,
    val DATE: String,
    val DESCRIPTION: String,
    val PROGRESS: ArrayList<Progress>
)

data class Progress(
    val PROGNO: Int,
    val PCONTENT: String
)