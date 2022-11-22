package com.example.test10

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentMain1 : Fragment() {

    private var mAdapter: RecyclerAdapter? = null
    private var list: ArrayList<CompanyData>? = null
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

            ///////이부분에을 자신의 프로젝트에 맞게 설정하면 됨
            //다음 페이지? 내용을 불러오는 부분
            val start: Int = mAdapter?.itemCount!!
            val end = start + 15
            for (i in start + 1..end) {
                list!!.add(CompanyData(
                    ContextCompat.getDrawable(requireContext(), R.drawable.mypage)!!,
                    "" + i,
                    "name $i"))
            }
            //////////////////////////////////////////////////
            mAdapter?.addItemMore(list)
            mAdapter?.setMoreLoading(false)
        }, 1000)
    }

    private fun loadData() {
        for (i in 1..20) {
            list!!.add(
                CompanyData(
                    ContextCompat.getDrawable(requireContext(), R.drawable.mypage)!!,
                    "" + i,
                    "name $i"
                )
            )
        }
        mAdapter?.addAll(list)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_main_1, container, false)

        list = ArrayList()
        val mRecyclerView = view.findViewById<View>(R.id.lstCompany) as RecyclerView
        val mLayoutManager = LinearLayoutManager(requireContext())
        mRecyclerView.layoutManager = mLayoutManager
        mAdapter = RecyclerAdapter(this)
        mAdapter!!.setLinearLayoutManager(mLayoutManager)
        mAdapter!!.setRecyclerView(mRecyclerView)
        mRecyclerView.adapter = mAdapter

        return view
    }

    override fun onResume() {
        super.onResume()
    }

}