package com.example.test10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.widget.Button
import android.widget.Toast
import android.view.View
import android.view.ViewGroup

class Fragment4 : Fragment(),MainActivity.onBackPressedListener {


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
        return inflater.inflate(R.layout.fragment_3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btn_overview = view.findViewById<Button>(R.id.event_overview)
        val btn_check_attend = view.findViewById<Button>(R.id.check_attendence)
        val btn_confirm_attend = view.findViewById<Button>(R.id.confirm_attendence)
        val btn_vote = view.findViewById<Button>(R.id.vote)
        val btn_docs = view.findViewById<Button>(R.id.docs)

        btn_overview.setOnClickListener {
            Toast.makeText(requireContext(), "행사 개요", Toast.LENGTH_SHORT).show()
        }

        btn_check_attend.setOnClickListener {
            Toast.makeText(requireContext(), "참석체크", Toast.LENGTH_SHORT).show()
        }

        btn_confirm_attend.setOnClickListener {
            Toast.makeText(requireContext(), "참석자 확인", Toast.LENGTH_SHORT).show()
        }

        btn_vote.setOnClickListener {
            Toast.makeText(requireContext(), "안건 투표", Toast.LENGTH_SHORT).show()
        }

        btn_docs.setOnClickListener {
            Toast.makeText(requireContext(), "행사 자료", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
}