package com.example.test10

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.test10.databinding.FragmentVotingBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentVoting : Fragment(),MainActivity.onBackPressedListener {

    private lateinit var binding: FragmentVotingBinding
    private var ssn = "D1017"
    private var eventNo = 1
    private var voteNo = 1
    private var myAnswer = 0

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
        val retrofitService = RetrofitClass.api.getVoteInfo(ssn, eventNo, voteNo)
        retrofitService.enqueue(object : Callback<VoteInfo> {
            override fun onResponse(call: Call<VoteInfo>, response: Response<VoteInfo>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("YMC", "성공 "+body.toString())
                    body?.let {
                        binding.voteAgenda.text = body.AGENDA
                        binding.voteContent.text = body.CONTENT

                        myAnswer = body.ANSWER
                        if(myAnswer == 0) binding.voteAnswer.text = "투표 미완료"
                        else if (myAnswer == 1) binding.voteAnswer.text = "찬성하셨습니다"
                        else if (myAnswer == 2) binding.voteAnswer.text = "반대하셨습니다"
                        else if (myAnswer == 3) binding.voteAnswer.text = "기권하셨습니다"
                    }
                }
            }

            override fun onFailure(call: Call<VoteInfo>, t: Throwable) {
                Log.d("YMC", "onFailure 에러 " + t.message.toString())
            }
        })

        binding.voteItem1.setOnClickListener {
            requestParticipating(1) {
                myAnswer = 1
            }
        }
        binding.voteItem2.setOnClickListener {
            requestParticipating(2) {
                myAnswer = 2
            }
        }
        binding.voteItem3.setOnClickListener {
            requestParticipating(3) {
                myAnswer = 3
            }
        }
    }

    private fun requestParticipating(answer: Int, dataChanged: () -> Unit) {
        val retrofitService = RetrofitClass.api.participateVote(ssn, eventNo, voteNo, answer)
        retrofitService.enqueue(object : Callback<checkBooleanClass>{
            override fun onResponse(
                call: Call<checkBooleanClass>,
                response: Response<checkBooleanClass>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("YMC", "성공 "+body.toString())
                    body?.let {
                        if (body.checkBoolean){
                            if(answer == 1){
                                binding.voteAnswer.text = "찬성하셨습니다"
                            }else if(answer == 2){
                                binding.voteAnswer.text = "반대하셨습니다"
                            }else if(answer == 3){
                                binding.voteAnswer.text = "기권하셨습니다"
                            }else{
                                binding.voteAnswer.text = "투표 미완료"
                            }
                            dataChanged()
                        } else{
                            Log.d("YMC", " #투표 #실패..")
                        }
                    }
                }
            }
            override fun onFailure(call: Call<checkBooleanClass>, t: Throwable) {
                Log.d("YMC", "onFailure 에러 " + t.message.toString())
            }
        })
    }
}
