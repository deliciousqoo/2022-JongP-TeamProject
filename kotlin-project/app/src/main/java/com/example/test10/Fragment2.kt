package com.example.test10

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import kotlinx.android.synthetic.main.fragment_2.*


class Fragment2 : Fragment(), MainActivity.onBackPressedListener {

    lateinit var mainActivity: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn1 = view.findViewById<Button>(R.id.btn1)

        btn1.setOnClickListener {
            Toast.makeText(requireContext(), "행사 1", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {

        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
        //requireActivity().supportFragmentManager.popBackStack()
    }


}