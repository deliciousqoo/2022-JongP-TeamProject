package com.example.test10

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.test10.databinding.ItemVoteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerAdapterManageVoteList(
    val items: ArrayList<VoteItem>)
    : RecyclerView.Adapter<RecyclerAdapterManageVoteList.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemVoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position]){
            notifyItemChanged(position)
        }
    }
    override fun getItemCount(): Int {
        return items.count()
    }
    class ViewHolder(val binding: ItemVoteBinding) : RecyclerView.ViewHolder(binding.root) {
        var voteNo = 0
        lateinit var item:VoteItem
        fun bind(titem: VoteItem, dataChanged: () -> Unit) {
            item = titem
            voteNo = item.voteno
            binding.voteTitle.text = item.title
            binding.voteExplain.text = item.explain

            if(item.status == 0){
                setReady(){
                    dataChanged()
                }
            }else if(item.status == 1) {
                setStarted(false,""){
                    dataChanged()
                }
            }else{
                setEnded(false,""){
                    dataChanged()
                }
            }

        }
        fun setReady(dataChanged: () -> Unit){
            binding.startVote.setBackgroundColor(Color.parseColor("#8BC34A"))
            binding.endVote.setBackgroundColor(Color.parseColor("#FF9F9F9F"))
            binding.startVote.setOnClickListener {
                requestChangeVoteStatus(true){
                    dataChanged()
                }
            }
            binding.endVote.setOnClickListener {
                Toast.makeText(
                    it.context,
                    "?????? ???????????? ?????? ?????? ?????????.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        fun setStarted(isChanged:Boolean, time:String, dataChanged: () -> Unit){
            if(isChanged){
                binding.startVoteTime.text = "?????? ?????? ?????? : "+time
            }
            else {
                binding.startVoteTime.text = "?????? ?????? ?????? : " + item.starttime
            }
            binding.startVote.setBackgroundColor(Color.parseColor("#FF9F9F9F"))
            binding.endVote.setBackgroundColor(Color.parseColor("#8BC34A"))
            binding.startVote.setOnClickListener {
                Toast.makeText(
                    it.context,
                    "?????? ????????? ?????? ?????????.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            binding.endVote.setOnClickListener {
                requestChangeVoteStatus(false){
                    dataChanged()
                }
            }
        }
        fun setEnded(isChanged:Boolean, time:String, dataChanged: () -> Unit){
            if(isChanged){
                binding.endVoteTime.text = "?????? ?????? ?????? : "+time
            }else {
                binding.startVoteTime.text = "?????? ?????? ?????? : " + item.starttime
                binding.endVoteTime.text = "?????? ?????? ?????? : " + item.endtime
            }
            binding.endVote.setBackgroundColor(Color.parseColor("#FF9F9F9F"))
            binding.startVote.setBackgroundColor(Color.parseColor("#FF9F9F9F"))
            binding.startVote.setOnClickListener {
                Toast.makeText(
                    it.context,
                    "?????? ????????? ?????? ?????????.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            binding.endVote.setOnClickListener {
                Toast.makeText(
                    it.context,
                    "?????? ????????? ?????? ?????????.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        fun requestChangeVoteStatus(isStart:Boolean,dataChanged: () -> Unit){
            val eventNo = DataClassClient.currentEvent
            val retrofitService = ClassSingleRetrofit.api.changeVoteStatus(eventNo,voteNo,isStart)
            retrofitService.enqueue( object : Callback<checkBooleanNTimeClass>{
                override fun onResponse(
                    call: Call<checkBooleanNTimeClass>,
                    response: Response<checkBooleanNTimeClass>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        Log.d("YMC", "?????? "+body.toString())
                        body?.let{
                            if(body.checkBoolean){
                                if(isStart){
                                    setStarted(true,body.time, dataChanged)
                                }
                                else{
                                    setEnded(true,body.time, dataChanged)
                                }
                            }
                        }

                    }
                }
                override fun onFailure(call: Call<checkBooleanNTimeClass>, t: Throwable) {
                    Log.d("YMC", "onFailure ?????? " + t.message.toString())
                }

            })
        }
    }



}