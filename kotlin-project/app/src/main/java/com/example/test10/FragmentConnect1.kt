package com.example.test10

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import retrofit2.*

class FragmentConnect1 : Fragment(),MainActivity.onBackPressedListener {

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

        return inflater.inflate(R.layout.fragment_connect_1, container, false)
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

    // 위의 코드 대신 데이터 가져오는 함수 넣어야...
    /*val callGetEventInfo = RetrofitClass.api.getEventInfo(1)

    callGetEventInfo.enqueue(object  : Callback<EventInfo> {
        override fun onResponse(call: Call<EventInfo>, response: Response<EventInfo>) {
            if (response.isSuccessful){
                var result: EventInfo? = response.body()
                Log.d("YMC","onResponse 성공: "+result.toString());
            }else{
                Log.d("YMC","onResponse 실패")
            }
        }

        override fun onFailure(call: Call<AttendList>, t: Throwable) {
            Log.d("YMC","onFailure 에러 "+t.message.toString());
        }
    })*/
}