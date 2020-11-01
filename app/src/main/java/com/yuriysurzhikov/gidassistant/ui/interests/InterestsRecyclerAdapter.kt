package com.yuriysurzhikov.gidassistant.ui.interests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.yuriysurzhikov.gidassistant.R
import com.yuriysurzhikov.gidassistant.model.Interest

class InterestsRecyclerAdapter(var list: List<Interest>) :
    RecyclerView.Adapter<InterestsRecyclerAdapter.InterestsViewHolder>() {

    fun updateList(list: List<Interest>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InterestsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item_interests, parent, false)
        return InterestsViewHolder(view)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: InterestsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class InterestsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var title: TextView = view.findViewById(R.id.interests_id)

        fun bind(interest: Interest) {
            title.text = interest.id
        }
    }
}