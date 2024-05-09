package com.joabesantana.androidapiconsumer

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PhotoAdapter(private val photos: List<Photo>): RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    //Conecta os componestes ao adpater
    inner class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
    }

    //Infla meu XML
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    // Conecta os dados com os nossos componente
    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = photos[position]
        holder.titleTextView.text = photo.title
        Glide.with(holder.itemView.context).load(photo.thumbnailUrl).centerCrop()
            .into(holder.imageView)
    }
}