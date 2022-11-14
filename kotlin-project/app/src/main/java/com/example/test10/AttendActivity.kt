package com.example.test10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton

class AttendActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attend)

        val qrBtn = findViewById<ImageButton>(R.id.qrBtn)
        //val button1 = findViewById<Button>(R.id.button1)
        val codeBtn = findViewById<Button>(R.id.codeBtn)
        val qrIntent = Intent(this, ScannerActivity::class.java)
        val codeIntent = Intent(this, AttendCodeActivity::class.java)

        qrBtn.setOnClickListener {
            startActivity(qrIntent)
        }

        codeBtn.setOnClickListener {
            startActivity(codeIntent)
        }



    }
}