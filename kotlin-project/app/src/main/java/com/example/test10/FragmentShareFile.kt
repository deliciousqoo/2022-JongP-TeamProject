package com.example.test10

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentShareFileBinding.inflate(inflater, container, false)
        super.onViewCreated(requireView(), savedInstanceState)
        val webView: WebView = binding.webView
        val settings = webView.settings
        settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl("http://www.google.com")
        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

}


