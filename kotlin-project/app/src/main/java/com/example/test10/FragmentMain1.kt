package com.example.test10

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test10.databinding.FragmentMain1Binding
import android.view.ViewConfiguration

class FragmentMain1 : Fragment(),MainActivity.onBackPressedListener {

    private var mBinding: FragmentMain1Binding? = null
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

        mBinding = FragmentMain1Binding.inflate(inflater, container, false)
        val mActivity = activity as MainActivity

        binding.btn1.setOnClickListener {
            mActivity.changeFragment(1)
        }

        binding.btn1.setOnLongClickListener {
            mActivity.changeFragment(7)
            true
        }

        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
}