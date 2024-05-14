package com.joabesantana.androidapiconsumer

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


class MovieAdapter(context: Context, movies: List<Movie>) :
    ArrayAdapter<Movie?>(context, 0, movies) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        var itemListView = convertView
        if (itemListView == null) {
            itemListView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        }

        val movie: Movie? = getItem(position)

        val countTextView: TextView = itemListView!!.findViewById(R.id.voteCountTextView)
        val imageView: ImageView = itemListView.findViewById(R.id.imageView)

        countTextView.text = "${movie!!.vote_count.toString()} Ratings"
        Glide.with(context).load(getUrl(movie!!.poster_path)).centerCrop().into(imageView)

        return itemListView
    }

    private fun getUrl(path: String): String {
        return "https://image.tmdb.org/t/p/w500$path"
    }
}

//class PhotoAdapter(private val photos: List<Photo>): RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
//
//    //Conecta os componestes ao adpater
//    inner class PhotoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
//        val imageView: ImageView = itemView.findViewById(R.id.imageView)
//        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
//    }
//
//    //Infla meu XML
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
//        return PhotoViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return photos.size
//    }
//
//    // Conecta os dados com os nossos componente
//    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
//        val photo = photos[position]
//        holder.titleTextView.text = photo.title
//        Glide.with(holder.itemView.context).load(photo.thumbnailUrl).centerCrop()
//            .into(holder.imageView)
//    }
//}