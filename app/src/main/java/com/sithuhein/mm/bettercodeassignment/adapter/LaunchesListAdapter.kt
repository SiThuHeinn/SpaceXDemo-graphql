package com.sithuhein.mm.bettercodeassignment.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sithuhein.mm.bettercodeassignment.R
import com.sithuhein.mm.domain.model.LaunchesPast

class LaunchesListAdapter : RecyclerView.Adapter<LaunchesListAdapter.LaunchesListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchesListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.launches_past_list_item, parent, false)
        return LaunchesListViewHolder(view)
    }

    override fun onBindViewHolder(holder: LaunchesListViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = launchesPast.size


    private val diffCallback = object : DiffUtil.ItemCallback<LaunchesPast>() {
        override fun areItemsTheSame(oldItem: LaunchesPast, newItem: LaunchesPast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LaunchesPast, newItem: LaunchesPast): Boolean {
           return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var launchesPast : List<LaunchesPast>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    class LaunchesListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
}