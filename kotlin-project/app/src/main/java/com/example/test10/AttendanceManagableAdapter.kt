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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AttendanceManagableAdapter(val items: ArrayList<Item>, val context: Context?): RecyclerView.Adapter<AttendanceManagableAdapter.ViewHolder>() {
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
        var ssn:String = ""
        var attend:Boolean = false
        init {
            binding.atteBtn.setOnClickListener {
                requestChangeAttend()
            }
        }
        fun bind(item: Item, context: Context? ){
            ssn = item.SSN
            attend = item.ATTEND
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

        private fun requestChangeAttend(){
            val retrofitService = RetrofitClass.api.getChangeStatus(ssn,attend,1)
            retrofitService.enqueue(object : Callback<checkBooleanClass>{
                override fun onResponse(
                    call: Call<checkBooleanClass>,
                    response: Response<checkBooleanClass>
                ) {
                    if (response.isSuccessful) {
                        val body = response.body()
                        Log.d("YMC", "성공 "+body.toString())
                        body?.let {
                            if (body.checkBoolean){
                                //#출석 #상태_변환 #성공적
                                attend = !attend
                                if (attend){
                                    //불참으로 바꾸기
                                    binding.atteBtn.text = "불참"
                                    binding.atteBtn.setBackgroundColor(Color.parseColor("#F44336"))
                                    binding.atteFrame.alpha = 0.5f
                                    attend = false
                                }else{
                                    //출석으로 바꾸기
                                    binding.atteBtn.text = "참석"
                                    binding.atteBtn.setBackgroundColor(Color.parseColor("#8BC34A"))
                                    binding.atteFrame.alpha = 1.0f
                                    attend = true
                                }
                            }else{
                                //#출석 #상태_변환 #실패..
                                Log.d("YMC", " #출석 #상태_변환 #실패..")
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<checkBooleanClass>, t: Throwable) {
                    Log.d("YMC", "onFailure 에러 " + t.message.toString())
                }

            })
        }
    }
}
