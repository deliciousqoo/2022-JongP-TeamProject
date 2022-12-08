package com.example.test10

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.test10.databinding.ItemViewVoteBinding

class RecyclerAdapter_FragmentVote(val datas: ArrayList<VoteContents>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is RecyclerAdapter_FragmentVote.Frag2ViewHolder3) {
            val binding = holder.binding

            binding.VoteTitle.text = "안건 ${position + 1} : " + datas[position].title
            binding.content.text = datas[position].writer + "| " + datas[position].status

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