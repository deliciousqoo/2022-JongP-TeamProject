package com.example.test10

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.test10.databinding.FragmentConnectMainBinding

class FragmentConnectMain : Fragment(),MainActivity.onBackPressedListener {

    private lateinit var binding: FragmentConnectMainBinding

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
        binding = FragmentConnectMainBinding.inflate(inflater, container, false)
        val mActivity = activity as MainActivity
        binding.eventOverview.setOnClickListener {
            mActivity.changeFragment(2)
        }
        binding.checkAttendence.setOnClickListener {
            mActivity.changeFragment(3)
        }
        binding.confirmAttendence.setOnClickListener {
            mActivity.changeFragment(4)
        }
        binding.vote.setOnClickListener {
            mActivity.changeFragment(6)
        }
        binding.docs.setOnClickListener {
            mActivity.changeFragment(13)
        }

        return binding.root
    }
    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
}