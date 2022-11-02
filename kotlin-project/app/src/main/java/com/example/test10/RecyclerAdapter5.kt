package com.example.test10

import android.R
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test10.databinding.ItemViewBinding
import com.example.test10.databinding.LoadingBinding
import kotlinx.android.synthetic.main.item_view.view.*


class RecyclerAdapter5(private val onLoadMoreListener: Fragment5) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_ITEM = 1
    private val VIEW_PROG = 0
    private val itemList: ArrayList<AgendaData?>
    private var mLinearLayoutManager: LinearLayoutManager? = null
    private var isMoreLoading = false
    private val visibleThreshold = 1
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    var lastVisibleItem = 0

    interface OnLoadMoreListener {
        fun onLoadMore()
    }

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
                if (!isMoreLoading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
                    onLoadMoreListener?.onLoadMore()
                    isMoreLoading = true
                }
            }
        })
    }

    override fun getItemViewType(position: Int): Int {
        return if (itemList[position] != null) VIEW_ITEM else VIEW_PROG
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_ITEM) {
            val binding = ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            //binding.foldingCell.setOnClickListener { binding.foldingCell.toggle(true) }
            return ItemViewHolder(binding)
        }else{
            val binding = LoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return LoadingViewHolder(binding)
        }
    }

    fun addAll(lst: List<AgendaData?>?) {
        itemList.clear()
        itemList.addAll(lst!!)
        notifyDataSetChanged()
    }

    fun addItemMore(lst: List<AgendaData?>?) {
        itemList.addAll(lst!!)
        notifyItemRangeChanged(0, itemList.size)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemViewHolder) {
            holder.tvItem.setText(itemList[position]?.getItem())
        }
    }

    fun setMoreLoading(isMoreLoading: Boolean) {
        this.isMoreLoading = isMoreLoading
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

    private inner class ItemViewHolder(binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        var tvItem: TextView
        var tvItem2: TextView

        init {
            tvItem = binding.nameUser
            //findViewById(R.id.tvItem)
            tvItem2 = binding.tecUser
        }
    }

    private inner class LoadingViewHolder(binding: LoadingBinding) : RecyclerView.ViewHolder(binding.root) {
        var progressBar: ProgressBar

        init {
            progressBar = binding.progressBar
        }
    }

}