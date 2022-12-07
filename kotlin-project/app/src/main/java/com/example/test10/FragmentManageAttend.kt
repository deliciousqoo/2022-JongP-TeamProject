package com.example.test10

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test10.databinding.FragmentConnect3Binding
import com.example.test10.databinding.FragmentManageAttendBinding
import kotlinx.android.synthetic.main.fragment_connect_1.*
import kotlinx.android.synthetic.main.fragment_vote_code.*
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
        recycler_view = binding.attelistrecycler
        loadData()
        return binding.root
    }
    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
    private fun setAdapter(items : ArrayList<Item>){
        var adapter = AttendanceAdapter(items, this.context)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this.context)
    }
    // 리사이클 뷰에 들어갈 데이터 쓰기
    private fun loadData() {
        val retrofitService = RetrofitClass.api.getItems(1)
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