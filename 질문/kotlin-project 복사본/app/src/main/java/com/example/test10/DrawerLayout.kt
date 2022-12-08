package com.example.test10

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import kotlinx.android.synthetic.main.main.*

class DrawerLayout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
    }

    override fun onBackPressed() {
        if(main_drawer_layout.isDrawerOpen(GravityCompat.END)){
            main_drawer_layout.closeDrawers()
            // 테스트를 위해 뒤로가기 버튼시 Toast 메시지
            Toast.makeText(this,"back btn clicked", Toast.LENGTH_SHORT).show()
        } else{
            super.onBackPressed()
        }
    }

}