package com.example.test10

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.test10.databinding.FragmentCreateVoteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
            writingData(mActivity)
        }
        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

    fun writingData(mActivity:MainActivity){
        var EventNo = DataClassClient.currentEvent
        var agenda = binding.VoteTitle.text.toString()
        var content = binding.explainVote.text.toString()

        val retrofitService = ClassSingleRetrofit.api.insertVote(EventNo,agenda,content)
        retrofitService.enqueue(object : Callback<checkBooleanClass> {
            override fun onResponse(
                call: Call<checkBooleanClass>,
                response: Response<checkBooleanClass>
            ) {
                val body = response.body()
                Log.d("YMC", "성공 "+body.toString())
                body?.let {
                    if (it.checkBoolean) {
                        Toast.makeText(requireContext(), "새로운 투표를 생성 하였습니다", Toast.LENGTH_SHORT)
                            .show()
                        mActivity.changeFragment(11)
                    }else{
                        Toast.makeText(requireContext(), "새 투표 생성에 실패 하였습니다", Toast.LENGTH_SHORT)
                            .show()
                        mActivity.changeFragment(11)
                    }
                }
            }

            override fun onFailure(call: Call<checkBooleanClass>, t: Throwable) {
                Log.d("YMC", "onFailure 에러 " + t.message.toString())
                Toast.makeText(requireContext(), "네트워크 오류", Toast.LENGTH_LONG)
            }

        })
    }

}
