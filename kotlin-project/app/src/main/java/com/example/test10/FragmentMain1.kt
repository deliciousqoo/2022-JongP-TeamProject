package com.example.test10

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test10.databinding.FragmentMain1Binding
import android.view.ViewConfiguration
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentMain1 : Fragment(),MainActivity.onBackPressedListener {

    private lateinit var binding: FragmentMain1Binding
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
        // 살려 놓을 코드
        binding = FragmentMain1Binding.inflate(inflater, container, false)
        // 곧 죽일 코드
//        val mActivity = activity as MainActivity
//        binding.btn1.setOnClickListener {
//            mActivity.changeFragment(1)
//        }
//        binding.btn1.setOnLongClickListener {
//            val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.manage_check_dialog, null)
//            val mBuilder = AlertDialog.Builder(requireContext())
//                .setView(mDialogView)
//                .setTitle("관리자 로그인 코드입력")
//
//            val  mAlertDialog = mBuilder.show()
//            val okButton = mDialogView.findViewById<Button>(R.id.successButton)
//            okButton.setOnClickListener {
//                val tempPassword = mDialogView.findViewById<EditText>(R.id.editText).text.toString()
//                if(tempPassword == "1234")    {
//                    mAlertDialog.dismiss()
//                    mActivity.changeFragment(7)
//                }
//            }
//            true
//        }
        // 코드 2
        recycler_view = binding.EventList

        loadData()
        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

    private fun loadData() {
        val retrofitService = ClassSingleRetrofit.api.getEventList()
        retrofitService.enqueue( object : Callback<EventList> {
            override fun onResponse(call: Call<EventList>, response: Response<EventList>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    Log.d("YMC", "성공 "+body.toString())
                    body?.let {
                        setAdapter(body.items)
                    }
                }
            }

            override fun onFailure(call: Call<EventList>, t: Throwable) {
                Log.d("YMC", "onFailure 에러 " + t.message.toString())
            }
        })
    }
    private fun setAdapter(items : ArrayList<EventItem>){
        var adapter = RecyclerAdapterEventList(items, this.context, mActivity)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this.context)
    }

}