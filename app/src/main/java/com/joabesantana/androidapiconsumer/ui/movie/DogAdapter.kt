package com.joabesantana.androidapiconsumer.ui.movie

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.joabesantana.androidapiconsumer.R
import com.joabesantana.androidapiconsumer.databinding.ActivityMovieListBinding
import com.joabesantana.androidapiconsumer.databinding.ItemDogBinding
import com.joabesantana.androidapiconsumer.databinding.ItemMovieBinding
import com.joabesantana.androidapiconsumer.model.Dog


class DogAdapter(context: Context, dogs: List<Dog>) : ArrayAdapter<Dog?>(context, 0, dogs) {

    private lateinit var binding: ItemDogBinding

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        binding = ItemDogBinding.inflate(LayoutInflater.from(parent.context))

        var itemListView = convertView
        if (itemListView == null) {
            itemListView = binding.root
        }

        val dog: Dog? = getItem(position)

        val breedTextView: TextView = binding.breedTextView
        val imageView: ImageView = binding.imageDogView

        breedTextView.text = dog!!.breeds.first().name
        Glide.with(context).load(dog!!.url).centerCrop().into(imageView)

        imageView.setOnClickListener(View.OnClickListener { view ->
            val intent: Intent = Intent(context, DogDetailActivity::class.java)
            intent.putExtra("dog", dog)
            view.context.startActivity(intent)
        })

        return itemListView
    }
}