package com.example.test10

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.test10.databinding.ItemConnect3Binding

class AttendanceAdapter(val items: ArrayList<Item>, val context: Context?): RecyclerView.Adapter<AttendanceAdapter.ViewHolder>() {
    internal interface OnItemClickListener {
        fun onItemClick(v: View?, position: Int) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemConnect3Binding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position],context)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    class ViewHolder(val binding: ItemConnect3Binding):RecyclerView.ViewHolder(binding.root){
        init {
            binding.atteBtn.setOnClickListener {
                Toast.makeText(binding.root.context,"${binding.atteName}님의 참석 여부를 바꿉니다.",Toast.LENGTH_LONG).show()

            }
        }
        fun bind(item: Item, context: Context? ){
            binding.atteName.text = "${item.NAME}"
            binding.atteRank.text = "${item.RANK}"
            if (item.ATTEND) {
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
}
