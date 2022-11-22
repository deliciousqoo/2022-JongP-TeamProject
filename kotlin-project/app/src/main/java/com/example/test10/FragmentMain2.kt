package com.example.test10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        mBinding = FragmentMain2Binding.inflate(inflater, container, false)
        val mActivity = activity as MainActivity
        binding.eventBtn1.setOnClickListener {
            mActivity.changeFragment(1)
        }

        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
}