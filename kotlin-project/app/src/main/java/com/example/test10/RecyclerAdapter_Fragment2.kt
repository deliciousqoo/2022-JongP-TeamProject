package com.example.test10

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test10.databinding.ItemViewRecyBinding

class RecyclerAdapter_Fragment2(val datas: ArrayList<Agenda>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class Frag2ViewHolder(val binding: ItemViewRecyBinding): RecyclerView.ViewHolder(binding.root), MainActivity.onBackPressedListener {
        override fun onBackPressed() {
            Log.d("Holder", "Holder")
        }
    }

    interface ItemClick{
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        Frag2ViewHolder(ItemViewRecyBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecyclerAdapter_Fragment2.Frag2ViewHolder) {
            val binding = holder.binding
            binding.agendaNo.text = "행사 " + datas[position].no
            binding.date.text =  datas[position].name

            Log.d("Adapter_frag2", "Adapter_frag2")

            if(itemClick != null) {
                binding.root.setOnClickListener(View.OnClickListener {
                    itemClick?.onClick(it, position)
                })
            }
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }
}