package com.example.test10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test10.databinding.FragmentConnect2Binding

class FragmentConnect2 : Fragment(),MainActivity.onBackPressedListener {

    private var mBinding: FragmentConnect2Binding? = null
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
        mBinding = FragmentConnect2Binding.inflate(inflater, container, false)
        val mActivity = activity as MainActivity
        binding.codeBtn.setOnClickListener  {
            mActivity.changeFragment(5)
        }

        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
}
