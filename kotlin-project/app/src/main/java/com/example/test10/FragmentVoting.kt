package com.example.test10

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.test10.databinding.FragmentVotingBinding
import kotlinx.android.synthetic.main.fragment_voting.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentVoting : Fragment(),MainActivity.onBackPressedListener {

    private lateinit var binding: FragmentVotingBinding

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
        binding = FragmentVotingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun loadData() {
        var eventNo = 1
        var voteNo = 1
        val retrofitService = RetrofitClass.api.getVoteInfo(eventNo, voteNo)
        retrofitService.enqueue(object : Callback<VoteInfo> {
            override fun onResponse(call: Call<VoteInfo>, response: Response<VoteInfo>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("YMC", "성공 "+body.toString())
                    body?.let {
                        voteAgenda.setText(body.AGENDA)
                        voteContent.setText(body.CONTENT)
                    }
                }
            }

            override fun onFailure(call: Call<VoteInfo>, t: Throwable) {
                Log.d("YMC", "onFailure 에러 " + t.message.toString())
            }
        })
    }
}
