package com.example.test10

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
}