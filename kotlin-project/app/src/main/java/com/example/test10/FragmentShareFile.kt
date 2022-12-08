package com.example.test10

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.test10.databinding.FragmentShareFileBinding


class FragmentShareFile : Fragment(),MainActivity.onBackPressedListener{

    private lateinit var binding: FragmentShareFileBinding

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
        val webView: WebView = binding.webView
        webView.settings.javaScriptEnabled = true
        val url = "https://docs.google.com/presentation/d/1F2ah2MX1SsLtYB-isxRNNXQfI0-73WMg/edit?usp=sharing&ouid=107207103656101966670&rtpof=true&sd=true"
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)
        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

}


