package com.example.test10

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ConnectActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_connect)
        val overview = findViewById<Button>(R.id.event_overview)
        val check = findViewById<Button>(R.id.check_attendence)
        val confirm = findViewById<Button>(R.id.confirm_attendence)
        val vote = findViewById<Button>(R.id.vote)
        val docs = findViewById<Button>(R.id.docs)

        overview.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.connect, Fragment4::class.java, null)
                .commit()
        }

        check.setOnClickListener {
            val attendIntent = Intent(this, AttendActivity::class.java)
            startActivity(attendIntent)
        }

        confirm.setOnClickListener {
            val intent = Intent(this, AttendanceActivity::class.java)
            startActivity(intent)
        }

        vote.setOnClickListener {
            var contentToast = Toast.makeText(this, "vote", Toast.LENGTH_SHORT)
            contentToast.show()
        }

        docs.setOnClickListener {
            var contentToast = Toast.makeText(this, "docs", Toast.LENGTH_SHORT)
            contentToast.show()
        }

    }

    interface onBackPressedListener {
        fun onBackPressed()
    }

    override fun onBackPressed(){
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if (fragment is onBackPressedListener) {
                (fragment as onBackPressedListener).onBackPressed()
                return
            }
        }
    }

    fun OnClick(view: View) {
        when(view.getId())    {
            R.id.item1 ->   {
                val homeIntent = Intent(this, MainActivity::class.java)
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(homeIntent)
            }
        }
    }
}