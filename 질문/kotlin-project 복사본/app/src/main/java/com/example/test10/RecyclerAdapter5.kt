package com.example.test10

import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test10.databinding.ItemView5Binding
import com.example.test10.databinding.LoadingBinding

class RecyclerAdapter5(private val onLoadMoreListener: FragmentConnect1) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_ITEM = 1
    private val VIEW_PROG = 0
    private val itemList: ArrayList<Progress?>
    private var mLinearLayoutManager: LinearLayoutManager? = null
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    var lastVisibleItem = 0

    init {
        itemList = ArrayList()
    }

    fun setLinearLayoutManager(linearLayoutManager: LinearLayoutManager?) {
        mLinearLayoutManager = linearLayoutManager
    }

    fun setRecyclerView(mView: RecyclerView) {
        mView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = recyclerView.childCount
                totalItemCount = mLinearLayoutManager!!.itemCount
                firstVisibleItem = mLinearLayoutManager!!.findFirstVisibleItemPosition()
                lastVisibleItem = mLinearLayoutManager!!.findLastVisibleItemPosition()
                Log.d("total", totalItemCount.toString() + "")
                Log.d("visible", visibleItemCount.toString() + "")
                Log.d("first", firstVisibleItem.toString() + "")
                Log.d("last", lastVisibleItem.toString() + "")
            }
        })
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemList[position] != null) VIEW_ITEM else VIEW_PROG
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_ITEM) {
            val binding = ItemView5Binding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ItemView5Holder(binding)
        }else{
            val binding = LoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return LoadingViewHolder(binding)
        }
    }

    fun addAll(lst: List<Progress?>?) {
        itemList.clear()
        itemList.addAll(lst!!)
        notifyDataSetChanged()
    }

    fun addItemMore(lst: List<Progress?>?) {
        itemList.addAll(lst!!)
        notifyItemRangeChanged(0, itemList.size)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemView5Holder) {
            holder.agendaItem.setText(itemList[position]?.PROGNO.toString()+".")
            holder.agendaItem2.setText(itemList[position]?.PCONTENT)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    fun setProgressMore(isProgress: Boolean) {
        if (isProgress) {
            Handler().post(Runnable {
                itemList.add(null)
                notifyItemInserted(itemList.size - 1)
            })
        } else {
            itemList.removeAt(itemList.size - 1)
            notifyItemRemoved(itemList.size)
        }
    }

    private inner class ItemView5Holder(binding: ItemView5Binding) : RecyclerView.ViewHolder(binding.root) {
        var agendaItem: TextView
        var agendaItem2: TextView

        init {
            agendaItem = binding.progNo
            //findViewById(R.id.tvItem)
            agendaItem2 = binding.pContent
        }
    }

    private inner class LoadingViewHolder(binding: LoadingBinding) : RecyclerView.ViewHolder(binding.root) {
        var progressBar: ProgressBar

        init {
            progressBar = binding.progressBar
        }
    }

 }