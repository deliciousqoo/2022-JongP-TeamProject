package com.example.test10

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test10.databinding.ItemViewVoteBinding

class RecyclerAdapterVoteList(
    val datas: ArrayList<VoteItem>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    class Frag2ViewHolder3(val binding: ItemViewVoteBinding): RecyclerView.ViewHolder(binding.root), MainActivity.onBackPressedListener {
        override fun onBackPressed() {
            Log.d("Holder", "Holder")
        }
    }

    interface ItemClick{
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        Frag2ViewHolder3(ItemViewVoteBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecyclerAdapterVoteList.Frag2ViewHolder3) {
            val binding = holder.binding

            binding.VoteTitle.text = "안건 ${position + 1} : " + datas[position].title
            when(datas[position].status){
                0->{
                    binding.content.text = "예정된 투표"
                }
                1->{
                    binding.content.text = "진행중인 투표"
                }
                2->{
                    binding.content.text = "종료된 투표"
                }
            }

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