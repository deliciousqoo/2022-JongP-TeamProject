package com.example.test10

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.test10.databinding.FragmentShareFileBinding
import kotlinx.android.synthetic.main.fragment_share_file.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentShareFile : Fragment(),MainActivity.onBackPressedListener{

    private lateinit var binding: FragmentShareFileBinding
    private lateinit var url:String
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShareFileBinding.inflate(inflater, container, false)
        webView = binding.webView
        webView.settings.javaScriptEnabled = true
        loadData()
        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

    private fun loadData(){
        val retrofitService = ClassSingleRetrofit.api.getEventFile(DataClassClient.currentEvent)
        retrofitService.enqueue(object : Callback<EventFile>{
            override fun onResponse(call: Call<EventFile>, response: Response<EventFile>) {
                if(response.isSuccessful){
                    val body = response.body()
                    Log.d("YMC", "성공 "+body.toString())
                    body?.let {
                        if(body.Url != null) {
                            url = body.Url
                            webView.webViewClient = WebViewClient()
                            webView.loadUrl(url)
                        }else{
                            Toast.makeText(requireContext(), "게시된 행사자료가 없습니다.", Toast.LENGTH_SHORT)
                        }
                    }
                }else{
                    Log.d("YMC", "무언가 실패")
                }
            }

            override fun onFailure(call: Call<EventFile>, t: Throwable) {
                Log.d("YMC", "onFailure 에러 " + t.message.toString())
            }

        })
    }
}


