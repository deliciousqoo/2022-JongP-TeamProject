package com.example.test10

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_vote.view.*

class RecyclerVoteAdapter : RecyclerView.Adapter<RecyclerVoteAdapter.ViewHolder>() {
    var items = mutableListOf<VoteData>()
    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerVoteAdapter.ViewHolder, position: Int) {

        val item = items[position]
        val listener = View.OnClickListener { it ->
            Toast.makeText(it.context, "Clicked -> title : ${item.title}, explain : ${item.explain}", Toast.LENGTH_SHORT).show()
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_vote, parent, false)
        return RecyclerVoteAdapter.ViewHolder(inflatedView)
    }

    // 각 항목에 필요한 기능을 구현
    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        fun bind(listener: View.OnClickListener, item: VoteData) {
            view.voteTitle.text = item.title
            view.voteExplain.text = item.explain
            view.startVoteTime.text = item.starttime
            view.endVoteTime.text = item.endtime
            view.setOnClickListener(listener)
        }
    }
}