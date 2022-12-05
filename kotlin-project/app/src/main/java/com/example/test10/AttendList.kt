package com.example.test10

data class AttendList (
    val items: List<Item>
)

data class Item(
    val SSN: String,
    val NAME: String,
    val RANK: String,
    val ATTEND: Boolean
)