package com.example.test10

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.test10.databinding.ItemConnect3Binding
import com.example.test10.databinding.ItemViewEventButtonBinding

class RecyclerAdapterEventList(
    val items: ArrayList<EventItem>, val context: Context?, val mActivity: MainActivity)
    : RecyclerView.Adapter<RecyclerAdapterEventList.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemViewEventButtonBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position],context, mActivity)
        val binding = holder.binding
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    class ViewHolder(val binding: ItemViewEventButtonBinding)
        :RecyclerView.ViewHolder(binding.root){
        fun bind(item: EventItem, context: Context?, mActivity: MainActivity){
            binding.eventBtn.text = item.Name +"\n장소 : "+item.Location + "\n일시 : "+item.Date
            binding.eventBtn.setOnClickListener {
                DataClassClient.currentEvent = item.EventNo
                mActivity.changeFragment(1)
            }
            binding.eventBtn.setOnLongClickListener {
                val mDialogView = LayoutInflater.from(context).inflate(R.layout.manage_check_dialog, null)
                val mBuilder = AlertDialog.Builder(context)
                    .setView(mDialogView)
                    .setTitle("관리자 로그인 코드입력")

                val  mAlertDialog = mBuilder.show()
                val okButton = mDialogView.findViewById<Button>(R.id.successButton)
                okButton.setOnClickListener {
                    val tempPassword = mDialogView.findViewById<EditText>(R.id.editText).text.toString()
                    if(tempPassword == "1234")    {
                        mAlertDialog.dismiss()
                        mActivity.changeFragment(7)
                    }
                }
                true
            }

        }
    }


}
