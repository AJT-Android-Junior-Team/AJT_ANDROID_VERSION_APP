package com.ajt.android_version_app

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AndroidAdapter(private val clickListener: (position : Int) -> Unit) : RecyclerView.Adapter<AndroidAdapter.ViewHolder>() {
    private var androidVersionsList = ArrayList<AndroidVersions>()

    override fun getItemCount() = androidVersionsList.size

    private fun getItem(position: Int) : AndroidVersions = androidVersionsList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false), clickListener)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    @SuppressLint("NotifyDataSetChanged")
    fun addAndroid(androidVersions: AndroidVersions) {
        androidVersionsList.add(androidVersions)
        notifyItemInserted(androidVersionsList.size)
    }

    class ViewHolder(view: View, listener: (position: Int) -> Unit) : RecyclerView.ViewHolder(view) {
        private val itemName : TextView? = view.findViewById(R.id.android_name)
        private val itemImage : ImageView? = view.findViewById(R.id.android_image)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener(position)
                }
            }
        }

        fun bind(androidVersions: AndroidVersions) {
            itemName?.text = androidVersions.versionName
            itemImage?.setImageResource(androidVersions.imageAndroid)
        }
    }
}