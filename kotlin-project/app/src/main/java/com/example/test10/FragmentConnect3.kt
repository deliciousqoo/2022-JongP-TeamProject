package com.example.test10

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test10.databinding.FragmentConnect3Binding
import retrofit2.*

class FragmentConnect3 : Fragment(),MainActivity.onBackPressedListener {

    private var mBinding: FragmentConnect3Binding? = null
    private val binding get() = mBinding!!
    var loadedData: AttendList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentConnect3Binding.inflate(inflater, container, false)
        val data :MutableList<AttendanceCheck> = loadData()
        var adapter = AttendanceAdapter()
        adapter.listData = data
        binding.attelistrecycler.adapter = adapter
        binding.attelistrecycler.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

    // 리사이클 뷰에 들어갈 데이터 쓰기
    fun loadData(): MutableList<AttendanceCheck> {
        getFromServer()
        val data: MutableList<AttendanceCheck> = mutableListOf()
        Log.d("YMC","테스트:"+loadedData.toString())
        if (loadedData != null) {
            var no = 0
            while( no < loadedData!!.items.size) {
                var item = loadedData!!.items.get(no)
                val name = item.NAME
                val rank = item.RANK
                var attendance = item.ATTEND
                var box = AttendanceCheck(name, rank, attendance)
                data.add(box)
                no += 1
            }
        }else{
            for (no in 1..100) {
                val name = "홍길동"
                val rank = "회원 등급"
                var attendance = true
                if (no % 3 == 1) {
                    attendance = false
                }
                var box = AttendanceCheck(name,rank,attendance)
                data.add(box)
            }
        }
        return data
    }

    fun getFromServer (){
        val callGetAttendList = RetrofitClass.api.getItems(1)
        callGetAttendList.enqueue(object  : Callback<AttendList>{
            override fun onResponse(call: Call<AttendList>, response: Response<AttendList>) {
                response.takeIf { it.isSuccessful }
                    ?.body()
                    ?.let { it ->
                        // do something
                        loadedData = response.body()
                        Log.d("YMC","onResponse 성공: "+loadedData.toString())
                    } ?: Log.d("YMC","onResponse 실패")
            }
            override fun onFailure(call: Call<AttendList>, t: Throwable) {
                Log.d("YMC","onFailure 에러 "+t.message.toString())
            }
        })
    }

}
