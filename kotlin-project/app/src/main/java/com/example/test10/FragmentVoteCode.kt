package com.example.test10

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test10.databinding.FragmentVoteCodeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentVoteCode : Fragment(),MainActivity.onBackPressedListener {

    private lateinit var binding: FragmentVoteCodeBinding
    private lateinit var recycler_view: RecyclerView
    val mActivity by lazy {
        requireActivity() as MainActivity
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val data = ArrayList<VoteContents>()
//        data.add(VoteContents("lorem ipsum", "관리자", "투표 종료"))
//        data.add(VoteContents("lorem ipsum", "노준수", "투표 진행중"))
//        data.add(VoteContents("lorem ipsum", "관리자", "투표 종료"))
//        data.add(VoteContents("lorem ipsum", "노준수", "투표 진행중"))
//        data.add(VoteContents("lorem ipsum", "관리자", "투표 종료"))
//        data.add(VoteContents("lorem ipsum", "노준수", "투표 진행중"))
//        data.add(VoteContents("lorem ipsum", "관리자", "투표 종료"))
//        data.add(VoteContents("lorem ipsum", "노준수", "투표 진행중"))
//
//
//        binding = FragmentVoteCodeBinding.inflate(inflater, container, false)
//        val mActivity = activity as MainActivity
//
//        binding.recyclerView.layoutManager = LinearLayoutManager(mActivity)
//        var adapter = RecyclerAdapter_FragmentVote(data)
//        adapter!!.itemClick = object : RecyclerAdapter_FragmentVote.ItemClick{
//            override fun onClick(view: View, position: Int) {
//                mActivity.changeFragment(10)
//
//            }
//        }
//        binding.recyclerView.adapter = adapter
        binding = FragmentVoteCodeBinding.inflate(inflater, container, false)
        recycler_view = binding.recyclerView
        loadData()

        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
    private fun setAdapter(items : ArrayList<VoteItem>){
        var adapter =  VoteAdapter(items)
        adapter!!.itemClick = object : VoteAdapter.ItemClick{
            override fun onClick(view: View, position: Int) {
                mActivity.changeFragment(10)
            }
        }
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this.context)
    }
    private fun loadData() {
        val eventNo = 1
        val retrofitService = RetrofitClass.api.getVoteItems(eventNo)
        retrofitService.enqueue( object : Callback<VoteList> {
            override fun onResponse(
                call: Call<VoteList>,
                response: Response<VoteList>)
            {
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("YMC", "성공 "+body.toString())
                    body?.let {
                        setAdapter(body.items)
                    }
                }
            }

            override fun onFailure(call: Call<VoteList>, t: Throwable) {
                Log.d("YMC", "onFailure 에러 " + t.message.toString())
            }
        })
    }

}