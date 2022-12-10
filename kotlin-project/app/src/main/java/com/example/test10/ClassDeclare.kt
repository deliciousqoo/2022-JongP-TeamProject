package com.example.test10

data class checkBooleanClass (
    val checkBoolean : Boolean
)

data class checkBooleanNTimeClass (
    val checkBoolean : Boolean,
    val time : String
)

data class AttendList (
    val items: ArrayList<Item>
)

data class Item(
    val SSN: String,
    val NAME: String,
    val RANK: String,
    var ATTEND: Boolean
)

data class VoteList (
    val items: ArrayList<VoteItem>
)

data class VoteItem (
    var title: String,
    var status: Int,
    var voteno: Int,
    var explain:String,
    var starttime:String,
    var endtime:String
)

data class VoteInfo (
    var VOTENO: Int,
    var AGENDA: String,
    var CONTENT: String,
    var STATUS: Int,
    // 0: 예정  1: 진행 중  2: 완료
    var ANSWER: Int
    // 1: 찬성  2: 반대  3: 기권
)

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