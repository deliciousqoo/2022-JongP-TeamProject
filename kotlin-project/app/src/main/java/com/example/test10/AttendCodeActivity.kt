package com.example.test10

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AttendCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attend_code)
        val checkBtn = findViewById<Button>(R.id.checkBtn)

        checkBtn.setOnClickListener {
            val attendPassword = findViewById<EditText>(R.id.attendPassword)
            val tempPassword = attendPassword.text.toString()
            val preIntent = Intent(this, MainActivity::class.java)

            if(tempPassword == "1234")  {
                Toast.makeText(this, "출석되었습니다", Toast.LENGTH_LONG).show()
                preIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                preIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(preIntent)
            }
            else    {
                Toast.makeText(this, "잘못된 입력입니다", Toast.LENGTH_LONG).show()
            }
        }
    }
}