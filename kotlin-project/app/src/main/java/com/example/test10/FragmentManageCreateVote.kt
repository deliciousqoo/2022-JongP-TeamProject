package com.example.test10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test10.databinding.FragmentCreateVoteBinding

class FragmentManageCreateVote : Fragment(),MainActivity.onBackPressedListener {

    private lateinit var binding: FragmentCreateVoteBinding

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
        binding = FragmentCreateVoteBinding.inflate(inflater, container, false)
        val mActivity = activity as MainActivity

        binding.doneCreate.setOnClickListener {
            mActivity.changeFragment(11)
        }
        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
}
