package com.example.test10

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test10.databinding.FragmentManageVoteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentManageVote : Fragment(),MainActivity.onBackPressedListener {

    private lateinit var binding: FragmentManageVoteBinding
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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentManageVoteBinding.inflate(inflater, container, false)
//        val mActivity = activity as MainActivity
//        val data:MutableList<VoteData> = loadVote()
//        var adapter = RecyclerVoteAdapter()
//        adapter.items = data
//        binding.votelist.adapter = adapter
//        binding.votelist.layoutManager = LinearLayoutManager(requireContext())
        recycler_view = binding.votelist

        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
    private fun setAdapter(items : ArrayList<VoteItem>){
        var adapter = RecyclerVoteAdapter(items)
//        adapter!!.itemClick = object : VoteAdapter.ItemClick{
//            override fun onClick(view: View, position: Int) {
//                clientDataClass.currentAgenda = items[position].voteno
//                mActivity.changeFragment(10)
//            }
//        }
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this.context)
    }
    private fun loadData() {
        val eventNo = 1
        val retrofitService = RetrofitClass.api.getVoteItems(eventNo)
        retrofitService.enqueue( object : Callback<VoteList> {
            override fun onResponse(
                call: Call<VoteList>,
                response: Response<VoteList>
            )
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
