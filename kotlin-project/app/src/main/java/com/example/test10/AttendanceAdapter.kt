package com.example.test10

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.test10.databinding.ItemRecyclerBinding

class AttendanceAdapter: RecyclerView.Adapter<Holder>() {
    var listData = mutableListOf<AttendanceCheck>()

    internal interface OnItemClickListener {
        fun onItemClick(v: View?, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val box = listData.get(position)
        holder.setAtteCheckBox(box)
    }

    override fun getItemCount(): Int {
        return listData.size
    }
}
class Holder(val binding: ItemRecyclerBinding):RecyclerView.ViewHolder(binding.root){
    init {
        binding.atteBtn.setOnClickListener {
            Toast.makeText(binding.root.context,"${binding.atteName}님의 참석 여부를 바꿉니다.",Toast.LENGTH_LONG).show()

        }
    }

    fun setAtteCheckBox(box: AttendanceCheck){
        binding.atteName.text = "${box.name}"
        binding.atteRank.text = "${box.rank}"
        if (box.attendance) {
            binding.atteBtn.text = "참석"
            binding.atteBtn.setBackgroundColor(Color.parseColor("#8BC34A"))
            binding.atteFrame.alpha = 1.0f
        }
        else {
            binding.atteBtn.text = "불참"
            binding.atteBtn.setBackgroundColor(Color.parseColor("#F44336"))
            binding.atteFrame.alpha = 0.5f
        }
    }
}