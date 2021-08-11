package com.sithuhein.mm.bettercodeassignment.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sithuhein.mm.bettercodeassignment.R
import com.sithuhein.mm.domain.model.LaunchesPast
import kotlinx.android.synthetic.main.launches_past_list_item.view.*
import javax.inject.Inject

class LaunchesListAdapter @Inject constructor() : RecyclerView.Adapter<LaunchesListAdapter.LaunchesListViewHolder>() {

    private var onItemClick : ((launchPast : LaunchesPast) -> Unit)? = null


    fun setItemClickListener(onClick : (launchesPast : LaunchesPast) -> Unit) {
        onItemClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchesListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.launches_past_list_item, parent, false)
        return LaunchesListViewHolder(view)
    }

    override fun onBindViewHolder(holder: LaunchesListViewHolder, position: Int) {
        val mLaunchesPast = launchesPast[position]
        Glide.with(holder.itemView.context)
            .load(mLaunchesPast.links?.mission_patch)
            .placeholder(R.drawable.ic_undraw_placeholder)
            .into(holder.itemView.item_img)
        holder.itemView.item_name.text = mLaunchesPast.mission_name
        holder.itemView.item_date.text = mLaunchesPast.launch_date_local
        holder.itemView.setOnClickListener {
            onItemClick?.let { onClick -> onClick(mLaunchesPast) }
        }
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