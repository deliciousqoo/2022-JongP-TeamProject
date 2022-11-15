package com.example.test10

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.test10.databinding.ActivityAttendanceBinding

class AttendanceActivity : AppCompatActivity() {
    val binding by lazy { ActivityAttendanceBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }
}
