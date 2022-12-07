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

class FragmentMain1 : Fragment(),MainActivity.onBackPressedListener {

    private var mBinding: FragmentMain1Binding? = null
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

        mBinding = FragmentMain1Binding.inflate(inflater, container, false)
        val mActivity = activity as MainActivity

        binding.btn1.setOnClickListener {
            mActivity.changeFragment(1)
        }

        binding.btn1.setOnLongClickListener {
            val mDialogView = LayoutInflater.from(requireContext()).inflate(R.layout.manage_check_dialog, null)
            val mBuilder = AlertDialog.Builder(requireContext())
                .setView(mDialogView)
                .setTitle("관리자 로그인 코드입력")

            val  mAlertDialog = mBuilder.show()
            val okButton = mDialogView.findViewById<Button>(R.id.successButton)
            okButton.setOnClickListener {
                val tempPassword = mDialogView.findViewById<EditText>(R.id.editText).text.toString()
                if(tempPassword == "1234")    {
                    mAlertDialog.dismiss()
                    mActivity.changeFragment(7)
                }
            }
            true
        }

        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }
}