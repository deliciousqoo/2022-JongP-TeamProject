package com.example.test10

data class VoteData (
    var title: String,
    var explain: String,
    var starttime: String,
    var endtime: String
)

data class VoteInfo (
    var VOTENO: Int,
    var AGENDA: String,
    var CONTENT: String,
    var STATUS: Int
    // 0: 예정  1: 진행 중  2: 완료
)