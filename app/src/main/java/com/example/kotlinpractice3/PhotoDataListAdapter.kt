package com.example.kotlinpractice3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PhotoDataListAdapter(private val data: List<PhotoData>) :
    RecyclerView.Adapter<PhotoDataListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val photoNameTextView: TextView = view.findViewById(R.id.photoNameTextView)
        val photoDateTimeTextView: TextView = view.findViewById(R.id.photoDateTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.photo_data_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = data[position]
        holder.photoNameTextView.text = currentItem.photoName
        holder.photoDateTimeTextView.text = currentItem.photoDateTime
    }

    override fun getItemCount(): Int {
        return data.size
    }

    data class PhotoData(var photoName: String, val photoDateTime: String)

}