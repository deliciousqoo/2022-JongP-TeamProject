
/*
class AttendanceActivity : AppCompatActivity() {
    val binding by lazy { ActivityAttendanceBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val data:MutableList<AttendanceCheck> = loadData()
        var adapter = AttendanceAdapter()
        adapter.listData = data
        binding.attelistrecycler.adapter = adapter

        binding.attelistrecycler.layoutManager = LinearLayoutManager(this)
    }

    fun loadData(): MutableList<AttendanceCheck> {
        val data: MutableList<AttendanceCheck> = mutableListOf()
        for (no in 1..100) {
            val name = "홍길동"
            val rank = "회원 등급"
            var attendance = true
            if (no % 3 == 1) {
                attendance = false
            }
            var box = AttendanceCheck(name,rank,attendance)
            data.add(box)
        }
        return data;
    }
}*/

package com.example.test10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test10.databinding.FragmentConnect3Binding
import com.example.test10.databinding.FragmentConnectMainBinding

class FragmentConnect3 : Fragment(),MainActivity.onBackPressedListener {

    private var mBinding: FragmentConnect3Binding? = null
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
        mBinding = FragmentConnect3Binding.inflate(inflater, container, false)
        val data:MutableList<AttendanceCheck> = loadData()
        var adapter = AttendanceAdapter()
        adapter.listData = data
        binding.attelistrecycler.adapter = adapter

        binding.attelistrecycler.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    override fun onBackPressed() {
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
    }

    fun loadData(): MutableList<AttendanceCheck> {
        val data: MutableList<AttendanceCheck> = mutableListOf()
        for (no in 1..100) {
            val name = "홍길동"
            val rank = "회원 등급"
            var attendance = true
            if (no % 3 == 1) {
                attendance = false
            }
            var box = AttendanceCheck(name,rank,attendance)
            data.add(box)
        }
        return data;
    }
}
