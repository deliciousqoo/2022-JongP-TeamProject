package com.example.test10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.test10.databinding.FragmentConnectMainBinding

class FragmentConnectMain : Fragment(),MainActivity.onBackPressedListener {

    private var mBinding: FragmentConnectMainBinding? = null
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
        mBinding = FragmentConnectMainBinding.inflate(inflater, container, false)
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
            var contentToast = Toast.makeText(requireContext(), "vote", Toast.LENGTH_SHORT)
            contentToast.show()
        }
        binding.docs.setOnClickListener {
            var contentToast = Toast.makeText(requireContext(), "docs", Toast.LENGTH_SHORT)
            contentToast.show()
        }

        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
}