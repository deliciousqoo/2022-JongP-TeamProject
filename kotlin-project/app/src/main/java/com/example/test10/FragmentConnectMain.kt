package com.example.test10

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.test10.databinding.FragmentConnectMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentConnectMain : Fragment(),MainActivity.onBackPressedListener {

    private lateinit var binding: FragmentConnectMainBinding

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
        binding = FragmentConnectMainBinding.inflate(inflater, container, false)
        val mActivity = activity as MainActivity
        binding.eventOverview.setOnClickListener {
            mActivity.changeFragment(2)
        }
        binding.checkAttendence.setOnClickListener {
            checkAttend(mActivity)
        }
        binding.confirmAttendence.setOnClickListener {
            mActivity.changeFragment(4)
        }
        binding.vote.setOnClickListener {
            mActivity.changeFragment(6)
        }
        binding.docs.setOnClickListener {
            mActivity.changeFragment(13)
        }

        return binding.root
    }
    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

    fun checkAttend(mActivity:MainActivity){
        val Ssn = DataClassClient.SSN
        val EventNo = DataClassClient.currentEvent
        val retrofitService = ClassSingleRetrofit.api.getCheckStatus(Ssn,EventNo)
        retrofitService.enqueue(object :Callback<checkBooleanClass>{
            override fun onResponse(
                call: Call<checkBooleanClass>,
                response: Response<checkBooleanClass>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("YMC", "성공 "+body.toString())
                    body?.let {
                        if (it.checkBoolean) {
                            Toast.makeText(requireContext(), "이미 출석을 완료 하였습니다", Toast.LENGTH_LONG)
                                .show()
                        }else{
                            mActivity.changeFragment(3)
                        }
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