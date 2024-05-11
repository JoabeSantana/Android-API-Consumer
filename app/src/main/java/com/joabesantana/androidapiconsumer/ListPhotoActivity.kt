package com.joabesantana.androidapiconsumer

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListPhotoActivity : AppCompatActivity() {

    private lateinit var gridView: GridView
    private lateinit var adapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_photo)

        gridView = findViewById(R.id.idGridViewPhotos)

        //adapter = PhotoAdapter(emptyList())
        //gridView.layoutManager = LinearLayoutManager(this)
        //recycleView.adapter = adapter

        //val photoService = RetrofitClient.createPhotoService()
        val photoService = RetrofitClient.createService(IPhotoService::class.java)
        val call = photoService.fetchPhotos()
        val context : Context  = this
        call.enqueue(object : Callback<List<Photo>>{
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    val photos = response.body()
                    if (photos != null) {
                        adapter = PhotoAdapter(context, photos)
                        gridView.adapter = adapter
                        adapter.notifyDataSetChanged()
                    }
                } else {
                    println("Erro ao buscar as fotos ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                println("Erro ao buscar as fotos ${t.message}")
            }

        })

    }
}