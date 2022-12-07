package com.example.test10

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.test10.databinding.FragmentAttendCodeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentAttendCode : Fragment(),MainActivity.onBackPressedListener {

    private var mBinding: FragmentAttendCodeBinding? = null
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
        mBinding = FragmentAttendCodeBinding.inflate(inflater, container, false)

        val mActivity = activity as MainActivity

        binding.checkBtn.setOnClickListener {
            val tempPassword = binding.attendPassword.text.toString()
            checkCode(tempPassword)

//            if(tempPassword == "1234")  {
//                Toast.makeText(requireContext(), "출석되었습니다", Toast.LENGTH_LONG).show()
//                mActivity.changeFragment(1)
//            }
//            else    {
//                Toast.makeText(requireContext(), "잘못된 입력입니다", Toast.LENGTH_LONG).show()
//            }
        }

        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

    fun attendSuccess(){
        val mActivity = activity as MainActivity
        Toast.makeText(requireContext(), "출석되었습니다", Toast.LENGTH_LONG).show()
        mActivity.changeFragment(1)
        //이동하고 나서도 입력 키보드가 올라와있는데 내려주실 수 있나요?
    }

    fun attendFail(){
        Toast.makeText(requireContext(), "잘못된 입력입니다", Toast.LENGTH_LONG).show()
    }

    private fun checkCode(InputedCode:String) {
        val retrofitService = RetrofitClass.api.getCodeCheck("D1017",InputedCode,1)
        retrofitService.enqueue( object : Callback<checkBooleanClass>{
            override fun onResponse(
                call: Call<checkBooleanClass>,
                response: Response<checkBooleanClass>
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("YMC", "성공 "+body.toString())
                    body?.let {
                        //setAdapter(body.items)
                        if(body.checkBoolean){
                            attendSuccess()
                        }
                        else{
                            attendFail()
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