package com.joabesantana.androidapiconsumer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListPhotoActivity : AppCompatActivity() {

    private lateinit var recycleView: RecyclerView
    private lateinit var adapter: PhotoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_photo)

        recycleView = findViewById(R.id.recycleView)

        adapter = PhotoAdapter(emptyList())
        recycleView.layoutManager = LinearLayoutManager(this)
        //recycleView.adapter = adapter

        //val photoService = RetrofitClient.createPhotoService()
        val photoService = RetrofitClient.createService(IPhotoService::class.java)
        val call = photoService.fetchPhotos()
        call.enqueue(object : Callback<List<Photo>>{
            override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                if (response.isSuccessful) {
                    val photos = response.body()
                    if (photos != null) {
                        adapter = PhotoAdapter(photos)
                        recycleView.adapter = adapter
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