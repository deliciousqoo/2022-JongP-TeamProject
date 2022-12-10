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
        recycler_view = binding.votelist
        loadData()
        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
    private fun setAdapter(items : ArrayList<VoteItem>){
        var adapter = RecyclerVoteAdapter(items)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this.context)
    }
    private fun loadData() {
        val eventNo = DataClassClient.currentEvent
        val retrofitService = ClassSingleRetrofit.api.getVoteItems(eventNo)
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
}
