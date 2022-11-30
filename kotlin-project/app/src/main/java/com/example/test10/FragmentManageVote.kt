package com.example.test10

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test10.databinding.FragmentConnect3Binding
import com.example.test10.databinding.FragmentMain2Binding
import com.example.test10.databinding.FragmentManageVoteBinding

class FragmentManageVote : Fragment(),MainActivity.onBackPressedListener {

    private var mBinding: FragmentManageVoteBinding? = null
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
        mBinding = FragmentManageVoteBinding.inflate(inflater, container, false)
        val mActivity = activity as MainActivity
        binding.createVote.setOnClickListener {
            var contentToast = Toast.makeText(requireContext(), "create vote", Toast.LENGTH_SHORT)
            contentToast.show()
            //startActivity(Intent(applicationContext, CreateVoteActivity::class.java))
        }

        val data:MutableList<VoteData> = loadVote()
        var adapter = RecyclerVoteAdapter()
        adapter.items = data
        binding.votelist.adapter = adapter

        binding.votelist.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

    fun loadVote(): MutableList<VoteData> {
        val data: MutableList<VoteData> = mutableListOf()
        for (no in 1..3) {
            var title = "투표제목"
            var explain = "투표설명"
            var starttime = "11월 29일 12시"
            var endtime = "11월 29일 1시"

            var box = VoteData(title, explain, starttime, endtime)
            data.add(box)
        }
        return data;
    }
}
