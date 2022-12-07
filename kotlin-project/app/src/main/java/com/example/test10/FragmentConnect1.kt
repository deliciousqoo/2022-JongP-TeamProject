package com.example.test10

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import retrofit2.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentConnect1 : Fragment(),MainActivity.onBackPressedListener {

    private var mAdapter: RecyclerAdapter5? = null
    private var list: ArrayList<Agenda>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
        onLoadMore()
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity_", "onStart")
        loadData()
    }

    //스크롤이 끝에 도달하였을 때 실행 내용
    fun onLoadMore() {
        Log.d("MainActivity_", "onLoadMore")
        mAdapter?.setProgressMore(true)
        Handler().postDelayed(Runnable {
            list!!.clear()
            mAdapter?.setProgressMore(false)
            mAdapter?.addItemMore(list)
        }, 1000)
    }

    private fun loadData() {
//        val retrofitService = RetrofitClass.api.getEventInfo(1)
//        retrofitService.enqueue(object : Callback<EventInfo> {
//            override fun onResponse(call: Call<EventInfo>, response: Response<EventInfo>) {
//                if (response.isSuccessful) {
//                    val body = response.body()
//                    Log.d("YMC", "성공 "+body.toString())
//                    body?.let {
//                        setAdapter(body.PROGRESS)
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<EventInfo>, t: Throwable) {
//                Log.d("YMC", "onFailure 에러 " + t.message.toString())
//            }
//        })

        for (i in 1..10) {
            list!!.add(
                Agenda(i,"agenda $i")
            )
        }
        mAdapter?.addAll(list)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_connect_1, container, false)

        list = ArrayList()
        val mRecyclerView = view.findViewById<View>(R.id.agendaList) as RecyclerView
        val mLayoutManager = LinearLayoutManager(requireContext())
        mRecyclerView.layoutManager = mLayoutManager
        mAdapter = RecyclerAdapter5(this)    //
        mAdapter!!.setLinearLayoutManager(mLayoutManager)
        mAdapter!!.setRecyclerView(mRecyclerView)
        mRecyclerView.adapter = mAdapter

        return view
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

    // 위의 코드 대신 데이터 가져오는 함수 넣어야...
    /*val callGetEventInfo = RetrofitClass.api.getEventInfo(1)

    callGetEventInfo.enqueue(object  : Callback<EventInfo> {
        override fun onResponse(call: Call<EventInfo>, response: Response<EventInfo>) {
            if (response.isSuccessful){
                var result: EventInfo? = response.body()
                Log.d("YMC","onResponse 성공: "+result.toString());
            }else{
                Log.d("YMC","onResponse 실패")
            }
        }

        override fun onFailure(call: Call<AttendList>, t: Throwable) {
            Log.d("YMC","onFailure 에러 "+t.message.toString());
        }
    })*/

}