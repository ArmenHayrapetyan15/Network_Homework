package com.example.network_homework

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(val context: Context, val userList: List<MyDataItem>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var userId : TextView
        var title : TextView

        init {
            userId = itemview.findViewById(R.id.userId)
            title = itemview.findViewById(R.id.Title)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.row_items, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userId.text = userList[position].id.toString()
        holder.title.text = userList[position].title
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}