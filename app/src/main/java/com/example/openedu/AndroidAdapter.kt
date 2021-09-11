package com.example.openedu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AndroidAdapter(private val clickListener: (position : Int) -> Unit) : RecyclerView.Adapter<AndroidAdapter.ViewHolder>(){
    private var android_lst = ArrayList<Android>()

    override fun getItemCount() = android_lst.size

    private fun getItem(position: Int) : Android = android_lst[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false), clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun addAndroid(android: Android) {
        android_lst.add(android)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View, listener: (position: Int) -> Unit) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.android_name)
        val image = view.findViewById<ImageView>(R.id.android_image)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener(position)
                }
            }
        }

        fun bind(android: Android) {
            name.text = android.title
            image.setImageResource(android.imageAndroid)
        }
    }
}