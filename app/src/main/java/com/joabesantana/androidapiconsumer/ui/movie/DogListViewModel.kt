package com.joabesantana.androidapiconsumer.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.joabesantana.androidapiconsumer.model.Dog
import com.joabesantana.androidapiconsumer.services.IDogService
import com.joabesantana.androidapiconsumer.services.RetrofitClient
import com.joabesantana.androidapiconsumer.services.RetrofitDogClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DogListViewModel : ViewModel() {

    private var dogListLiveData = MutableLiveData<MutableList<Dog>>()

    fun fetchDogs(concatResults: Boolean) {

        val dogService = RetrofitDogClient.createService(IDogService::class.java)

        val call = dogService.fetchDogs()

        call.enqueue(object : Callback<MutableList<Dog>> {

            override fun onResponse(call: Call<MutableList<Dog>>, response: Response<MutableList<Dog>>) {
                if (response.isSuccessful) {
                    val dogsResults = response.body()
                    if (dogsResults != null) {
                        if (concatResults && dogListLiveData.value != null) {
                            val results: MutableList<Dog> = ArrayList()
                            results.addAll(dogListLiveData.value!!)
                            results.addAll(dogsResults)
                            dogListLiveData.value = results
                        } else {
                            dogListLiveData.value = dogsResults
                        }
                    }
                } else {
                    println("Error to fetch Movies: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<MutableList<Dog>>, t: Throwable) {
                println("Error to fetch Movies: ${t.message}")
            }
        })
    }

    fun getDogListLiveData(): MutableLiveData<MutableList<Dog>> {
        return dogListLiveData
    }
}