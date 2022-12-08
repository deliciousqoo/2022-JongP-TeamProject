package com.example.test10

data class VoteList (
    val items: ArrayList<VoteItem>
)

data class VoteItem (
    var title: String,
    var status: Int,
    var voteno: Int
    )

