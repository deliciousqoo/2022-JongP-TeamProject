package com.example.test10

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test10.databinding.FragmentMain2Binding

class FragmentMain2 : Fragment(),MainActivity.onBackPressedListener {

    private var mBinding: FragmentMain2Binding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //Test data.
        val data = ArrayList<Agenda>()
        data.add(Agenda(1, "test"))
        data.add(Agenda(2, "test2"))
        data.add(Agenda(1, "test"))
        data.add(Agenda(2, "test2"))

        mBinding = FragmentMain2Binding.inflate(inflater, container, false)
        val mActivity = activity as MainActivity

        binding.recyclerView.layoutManager = LinearLayoutManager(mActivity)
        var adapter = RecyclerAdapter_Fragment2(data)
        adapter!!.itemClick = object : RecyclerAdapter_Fragment2.ItemClick{
            override fun onClick(view: View, position: Int) {
                mActivity.changeFragment(1)
            }
        }
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onBackPressed() {
        Log.d("Frag2Back", "Frag2Back")
    }
}