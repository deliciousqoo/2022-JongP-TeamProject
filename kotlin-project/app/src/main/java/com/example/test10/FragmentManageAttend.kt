package com.example.test10

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test10.databinding.FragmentManageAttendBinding
import retrofit2.*

class FragmentManageAttend : Fragment(),MainActivity.onBackPressedListener {

    private lateinit var binding: FragmentManageAttendBinding
    private lateinit var recycler_view: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentManageAttendBinding.inflate(inflater, container, false)
        val cName = DataClassClient.name
        binding.textView.text = cName + " 관리자님 환영합니다. \n버튼을 눌러 참석 여부를 변경할 수 있습니다."
        recycler_view = binding.attelistrecycler
        loadData()
        return binding.root
    }
    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
    private fun setAdapter(items : ArrayList<Item>){
        var adapter = RecyclerAdapterManagableAttendance(items, this.context)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this.context)
    }
    // 리사이클 뷰에 들어갈 데이터 쓰기
    private fun loadData() {
        val eventNo = DataClassClient.currentEvent
        val retrofitService = ClassSingleRetrofit.api.getItems(eventNo)
        retrofitService.enqueue(object : Callback<AttendList> {
            override fun onResponse(call: Call<AttendList>, response: Response<AttendList>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("YMC", "성공 "+body.toString())
                    body?.let {
                        setAdapter(body.items)
                    }
                }
            }

            override fun onFailure(call: Call<AttendList>, t: Throwable) {
                Log.d("YMC", "onFailure 에러 " + t.message.toString())
            }
        })
    }
}