package com.ajt.android_version_app.presentation.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ajt.android_version_app.R
import com.ajt.android_version_app.presentation.models.AndroidVersion

class AndroidAdapter(private val onClickListener: (position: Int) -> Unit) :
    RecyclerView.Adapter<AndroidAdapter.ViewHolder>() {
    private var androidVersionsList = ArrayList<AndroidVersion>()

    override fun getItemCount() = androidVersionsList.size

    private fun getItem(position: Int): AndroidVersion = androidVersionsList[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onClickListener(position)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAndroidList(androidVersion: List<AndroidVersion>) {
        androidVersionsList = ArrayList(androidVersion)
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val itemName: TextView? = view.findViewById(R.id.android_name)
        private val itemImage: ImageView? = view.findViewById(R.id.android_image)

        fun bind(androidVersion: AndroidVersion) {
            itemName?.text = androidVersion.versionName
            itemImage?.setImageResource(androidVersion.imageAndroid)
        }
    }
}