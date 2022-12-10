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
            if(item.status == 0){
                setReady()
            }else if(item.status == 1) {
                view.startVoteTime.text = "투표 시작 일자 : "+item.starttime
                setStarted()
            }else{
                view.startVoteTime.text = "투표 시작 일자 : "+item.starttime
                view.endVoteTime.text = "투표 종료 일자 : "+item.endtime
                setEnded()
            }
            //view.setOnClickListener(listener)
        }
        fun setReady(){
            view.startVote.setBackgroundColor(Color.parseColor("#8BC34A"))
            view.endVote.setBackgroundColor(Color.parseColor("#FF9F9F9F"))
            view.startVote.setOnClickListener {
                setStarted()
            }
            view.endVote.setOnClickListener {
                Toast.makeText(
                    it.context,
                    "아직 시작하지 않은 회의 입니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        fun setStarted(){
            view.startVote.setBackgroundColor(Color.parseColor("#FF9F9F9F"))
            view.endVote.setBackgroundColor(Color.parseColor("#8BC34A"))
            view.startVote.setOnClickListener {
                Toast.makeText(
                    it.context,
                    "이미 시작한 회의 입니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            view.endVote.setOnClickListener {
                setEnded()
            }
        }
        fun setEnded(){
            view.endVote.setBackgroundColor(Color.parseColor("#FF9F9F9F"))
            view.startVote.setBackgroundColor(Color.parseColor("#FF9F9F9F"))
            view.startVote.setOnClickListener {
                Toast.makeText(
                    it.context,
                    "이미 종료된 회의 입니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            view.endVote.setOnClickListener {
                Toast.makeText(
                    it.context,
                    "이미 종료된 회의 입니다.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


    override fun getItemCount(): Int {
        return items.count()
    }
}