package com.example.test10

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test10.databinding.FragmentConnect2Binding
import com.example.test10.databinding.FragmentManageMainBinding

class FragmentManageMain : Fragment(),MainActivity.onBackPressedListener {

    private lateinit var binding: FragmentManageMainBinding

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
        binding = FragmentManageMainBinding.inflate(inflater, container, false)
        val mActivity = activity as MainActivity
        binding.manageVote.setOnClickListener() {
            mActivity.changeFragment(8)
        }
        binding.manageAttendance.setOnClickListener {
            mActivity.changeFragment(12)
        }
        binding.manageData.setOnClickListener {
            mActivity.changeFragment(13)
        }

        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
}