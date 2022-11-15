package com.example.test10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test10.databinding.ActivityAttendanceBinding

class AttendanceActivity : AppCompatActivity() {
    val binding by lazy { ActivityAttendanceBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data:MutableList<AttendanceCheck> = loadData()
        var adapter = AttendanceAdapter()
        adapter.listData = data
        binding.attelistrecycler.adapter = adapter

        binding.attelistrecycler.layoutManager = LinearLayoutManager(this)
    }

    fun loadData(): MutableList<AttendanceCheck> {
        val data: MutableList<AttendanceCheck> = mutableListOf()
        for (no in 1..100) {
            val name = "홍길동"
            val rank = "회원 등급"
            var attendance = true
            if (no % 3 == 1) {
                attendance = false
            }
            var box = AttendanceCheck(name,rank,attendance)
            data.add(box)
        }
        return data;
    }
}
