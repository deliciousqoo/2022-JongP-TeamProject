package com.example.test10

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
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

    private final var FINISH_INTERVAL_TIME: Long = 2000
    private var backPressedTime : Long = 0
    lateinit var bottomMenu : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar) //커스텀한 toolbar를 액션바로 사용

        supportActionBar?.setDisplayShowTitleEnabled(false) //액션바 제목표시

        // 애플리케이션 실행 후 첫 화면 설정
        supportFragmentManager.beginTransaction()
            .replace(R.id.body_container, FragmentMain1::class.java, null)
            .commit()

        // 하단 네비게이션 바 클릭 이벤트 설정
        bottomNagivationView.run {
            setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.item1 -> {
                        clearBackStack()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.body_container, FragmentMain1::class.java, null)
                            .commit()
                        bottomMenu = "home"
                        true
                    }
                    R.id.item2 -> {
                        clearBackStack()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.body_container, FragmentMain2::class.java, null)
                            .commit()
                        bottomMenu = "category"
                        true
                    }
                    R.id.item3 -> {
                        clearBackStack()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.body_container, FragmentMain3::class.java, null)
                            .commit()
                        bottomMenu = "profile"
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
        if(count == 0)  {
            val tempTime = System.currentTimeMillis()
            val intervalTime = tempTime - backPressedTime
            if(!(0 > intervalTime || FINISH_INTERVAL_TIME < intervalTime))  {
                finishAffinity()
                System.runFinalization()
                System.exit(0)
            } else  {
                backPressedTime = tempTime
                Toast.makeText(this, "'뒤로' 버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
                return
            }
        }
        else    {
            supportFragmentManager.popBackStack()
            //Log.d("태그", "현재 프래그먼트 카운트 : " + supportFragmentManager.backStackEntryCount)
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
                    .setReorderingAllowed(true)
                    .addToBackStack("connectMain")
                    .commit()
            }
            2-> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.body_container, FragmentConnect1::class.java, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("connect1")
                    .commit()
            }
            3-> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.body_container, FragmentConnect2::class.java, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("connect2")
                    .commit()
            }
            4-> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.body_container, FragmentConnect3::class.java, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("connect3")
                    .commit()
            }
            5-> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.body_container, FragmentAttendCode::class.java, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("attendCode")
                    .commit()
            }
            6-> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.body_container, FragmentVoteCode::class.java, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("voteCode")
                    .commit()
            }
            7-> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.body_container, FragmentManageMain::class.java, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("manageMain")
                    .commit()
            }
            8-> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.body_container, FragmentManageVote::class.java, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("manageVote")
                    .commit()
            }
            9-> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.body_container, FragmentCreateVote::class.java, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("createVote")
                    .commit()
            }
            10-> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.body_container, FragmentVoting::class.java, null)
                    .setReorderingAllowed(true)
                    .addToBackStack("Voting")
                    .commit()
            }
            11-> {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.body_container, FragmentManageVote::class.java, null)
                    .commit()
            }
            12->    {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.body_container, FragmentManageAttend::class.java, null)
                    .commit()
            }
            13->    {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.body_container, FragmentShareFile::class.java, null)
                    .commit()
            }
        }
    }

    fun onClick(view: View) {

        when(view.id)   {
            R.id.qrBtn-> {
                startActivity(Intent(applicationContext, ScannerActivity::class.java))
            }
            R.id.createVote->   {
                changeFragment(9)
            }
        }
    }

    fun clearBackStack() {
        val fragmentManager: FragmentManager = supportFragmentManager
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}


private fun NavigationView.setNavigationItemSelectedListener(mainActivity: MainActivity) {
    TODO("Not yet implemented")
}
