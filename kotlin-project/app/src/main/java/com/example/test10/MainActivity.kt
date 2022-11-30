package com.example.test10

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.fragment_connect_2.*
import kotlinx.android.synthetic.main.main.*

data class CompanyData(
    var img : Drawable,
    var tec: String,
    val name: String,
    var item: String? = null
)

//행사안건데이터
data class Agenda(
    var no: Int,
    var name: String
){
    private var item: Agenda? = null

    fun getNo(): String? {
        return no.toString()
    }

    fun getAgName(): String? {
        return name
    }

    fun getItem(): Agenda? {
        return item
    }

    fun setItem(item: Agenda?) {
        this.item = item
    }

    fun Item(item: Agenda?) {
        this.item = item
    }
}

//하단네비
@Suppress("UNREACHABLE_CODE")
class MainActivity : AppCompatActivity() {


    private val frame: RelativeLayout by lazy { // activity_main의 화면 부분
        findViewById(R.id.body_container)
    }
     val bottomNagivationView: BottomNavigationView by lazy { // 하단 네비게이션 바
        findViewById(R.id.bottom_navigation)
    }

    private var backPressedTime : Long = 0
    lateinit var bottomMenu : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar) //커스텀한 toolbar를 액션바로 사용

        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바 제목표시


        // 애플리케이션 실행 후 첫 화면 설정
        supportFragmentManager.beginTransaction()
            .replace(R.id.body_container, FragmentMain1::class.java, null)
            .setReorderingAllowed(true)
            .addToBackStack(null)
            .commit()

        bottomMenu = "home"
        // 하단 네비게이션 바 클릭 이벤트 설정
        bottomNagivationView.run {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.item1 -> {
                         supportFragmentManager.beginTransaction()
                            .replace(R.id.body_container, FragmentMain1::class.java, null)
                            .commit()
                        bottomMenu = "home"
                        true
                    }
                    R.id.item2 -> {
                        //startActivity(Intent(applicationContext, Fragment2Activity::class.java))
                        //overridePendingTransition(0, 0)
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.body_container, FragmentMain2::class.java, null)
                            .commit()
                        bottomMenu = "menu"
                        true
                    }
                    R.id.item3 -> {

                        supportFragmentManager.beginTransaction()
                            .replace(R.id.body_container, FragmentMain3::class.java, null)
                            .commit()
                        bottomMenu = "item3"
                        true
                    }
                    else -> false
                }
            }
        }

    }


    //액션버튼 메뉴 액션바에 집어 넣기
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }


    //액션버튼 클릭 했을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.search_button -> {
                bottomMenu = "search"
                val intent = Intent(this,SpinnerActivity::class.java)
                startActivity(intent)
                return super.onOptionsItemSelected(item)
            }
            R.id.submenu_button -> {
                //메뉴 버튼 눌렀을 때
                main_drawer_layout.openDrawer(GravityCompat.END)// 네비게이션 드로어 열기
                return super.onOptionsItemSelected(item)
            }

            else -> {return super.onOptionsItemSelected(item)}
        }

        //        서브메뉴

        main_navigationView.setNavigationItemSelectedListener(this) //navigation 리스너

        when(item.itemId){
            R.id.account-> {Toast.makeText(this,"account clicked",Toast.LENGTH_SHORT).show()}
            R.id.head1-> {Toast.makeText(this,"item2 clicked",Toast.LENGTH_SHORT).show()}
            R.id.head2-> {Toast.makeText(this,"item3 clicked",Toast.LENGTH_SHORT).show()}
        }
        return false



    }

    interface onBackPressedListener {
        fun onBackPressed()
    }

    override fun onBackPressed() { //뒤로가기 처리

        val count = supportFragmentManager.backStackEntryCount
        if(bottomMenu.equals("home")){

            bottomNagivationView.selectedItemId = R.id.item1
            bottomMenu = "home"
            supportFragmentManager.beginTransaction()
                .replace(R.id.body_container, FragmentMain1::class.java, null)
                .setReorderingAllowed(true)
                .addToBackStack("1")
                .commit()
        }/*
        else if(bottomMenu.equals("menu")){
            Log.d("test", "test")
            bottomNagivationView.selectedItemId = R.id.item1
            bottomMenu = "home"
            supportFragmentManager.beginTransaction()
                .replace(R.id.body_container, FragmentMain1::class.java, null)
                .setReorderingAllowed(true)
                .addToBackStack("1")
                .commit()
        }*/
        else
        {
            if(main_drawer_layout.isDrawerOpen(GravityCompat.END)){//            Drawer page back
                main_drawer_layout.closeDrawers()
            } else {//뒤로가기 2번 종료
                if(backPressedTime +1500 > System.currentTimeMillis()){
                    super.onBackPressed()
                    finish()
                } else{
                    Toast.makeText(applicationContext,"'뒤로'가기 버튼을 한번더 누르면 종료됩니다.",Toast.LENGTH_LONG).show()
                }
                backPressedTime = System.currentTimeMillis()
            }
        }
    }

    // 화면 전환 구현 메소드
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(frame.id, fragment).commit()
    }

    fun changeFragment(index: Int)  {
        when(index) {
            1-> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.body_container, FragmentConnectMain::class.java, null)
                    .commit()
            }
            2-> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.body_container, FragmentConnect1::class.java, null)
                    .commit()
            }
            3-> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.body_container, FragmentConnect2::class.java, null)
                    .commit()
            }
            4-> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.body_container, FragmentConnect3::class.java, null)
                    .commit()
            }
            5-> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.body_container, FragmentAttendCode::class.java, null)
                    .commit()
            }
            6-> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.body_container, FragmentVoteCode::class.java, null)
                    .commit()
            }
        }
    }

    fun onClick(view: View) {

        when(view.id)   {
            R.id.qrBtn-> {
                startActivity(Intent(applicationContext, ScannerActivity::class.java))
            }
        }

    }
}


private fun NavigationView.setNavigationItemSelectedListener(mainActivity: MainActivity) {
    TODO("Not yet implemented")
}
