package com.example.test10

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_vote.view.*

class RecyclerVoteAdapter(val items: ArrayList<VoteItem>) : RecyclerView.Adapter<RecyclerVoteAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerVoteAdapter.ViewHolder, position: Int) {

        val item = items[position]
        val listener = View.OnClickListener { it ->
            Toast.makeText(
                it.context,
                "Clicked -> title : ${item.title}, explain : ${item.explain}",
                Toast.LENGTH_SHORT
            ).show()
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
        fun bind(listener: View.OnClickListener, item: VoteItem) {
            view.voteTitle.text = item.title
            view.voteExplain.text = item.explain
            //view.startVoteTime.text = item.starttime
            //view.endVoteTime.text = item.endtime
            view.setOnClickListener(listener)
            view.startVote.setOnClickListener {
                view.startVote.setBackgroundColor(Color.parseColor("#FF9F9F9F"))
                view.endVote.setBackgroundColor(Color.parseColor("#8BC34A"))
            }
            view.endVote.setOnClickListener {
                view.endVote.setBackgroundColor(Color.parseColor("#FF9F9F9F"))
                view.startVote.setBackgroundColor(Color.parseColor("#8BC34A"))
            }
        }

    }

    override fun getItemCount(): Int {
        return items.count()
    }
}