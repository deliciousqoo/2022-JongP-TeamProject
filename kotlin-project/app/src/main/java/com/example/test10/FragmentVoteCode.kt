package com.example.test10

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test10.databinding.FragmentVoteCodeBinding

class FragmentVoteCode : Fragment(),MainActivity.onBackPressedListener {

    private var mBinding: FragmentVoteCodeBinding? = null
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

        val data = ArrayList<VoteContents>()
        data.add(VoteContents("lorem ipsum", "관리자", "투표 종료"))
        data.add(VoteContents("lorem ipsum", "노준수", "투표 진행중"))
        data.add(VoteContents("lorem ipsum", "관리자", "투표 종료"))
        data.add(VoteContents("lorem ipsum", "노준수", "투표 진행중"))
        data.add(VoteContents("lorem ipsum", "관리자", "투표 종료"))
        data.add(VoteContents("lorem ipsum", "노준수", "투표 진행중"))
        data.add(VoteContents("lorem ipsum", "관리자", "투표 종료"))
        data.add(VoteContents("lorem ipsum", "노준수", "투표 진행중"))

        mBinding = FragmentVoteCodeBinding.inflate(inflater, container, false)
        val mActivity = activity as MainActivity

        binding.recyclerView.layoutManager = LinearLayoutManager(mActivity)
        var adapter = RecyclerAdapter_FragmentVote(data)
        adapter!!.itemClick = object : RecyclerAdapter_FragmentVote.ItemClick{
            override fun onClick(view: View, position: Int) {
                Toast.makeText(mActivity,"next_page",Toast.LENGTH_LONG).show()
                mActivity.changeFragment(10)

            }
        }
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
}