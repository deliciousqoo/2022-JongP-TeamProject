package com.example.test10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton

class AttendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgBtn1 = findViewById<ImageButton>(R.id.imageBtn1)
        //val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val qrIntent = Intent(this, ScannerActivity::class.java)
        val codeIntent = Intent(this, AttendCodeActivity::class.java)

        imgBtn1.setOnClickListener {
            Log.d("태그", "1번 클릭")
            startActivity(qrIntent)
        }

        button2.setOnClickListener {
            Log.d("태그", "2번 클릭")
            startActivity(codeIntent)
        }
    }
}