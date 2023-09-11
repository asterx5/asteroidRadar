package com.example.asteroidradar.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.asteroidradar.PictureOfDay
import com.example.asteroidradar.RequiredAsteroids
import com.example.asteroidradar.api.Api
import com.example.asteroidradar.database.Repository
import com.example.asteroidradar.database.getDatabase
import com.example.asteroidradar.model.Asteroid
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {


    private val repository = Repository(getDatabase(application))

    private val _pictureOfTheDay = MutableLiveData<PictureOfDay?>()
    val pictureOfTheDay : LiveData<PictureOfDay?>
    get() =  _pictureOfTheDay




    private val _asteroids = MutableLiveData<List<Asteroid>?>()

   val asteroids : LiveData<List<Asteroid>?>
   get() = _asteroids






    init {

        viewModelScope.launch(Dispatchers.IO) {
          launch {_asteroids.postValue(repository.getAll()) }
            async { repository.getAsteroids()
                _asteroids.postValue(repository.getAll()) }

            getPictureOfTheDay()


        }
    }



    private suspend fun getPictureOfTheDay(){

        try {
            val response = Api.service.getImageOfTheDay()
            val body = response.body()
            if (body!= null)_pictureOfTheDay.postValue(body)
        }catch (e:java.lang.Exception){
            Log.i("MyTag", e.message.toString())
        }
    }





      fun changeRequirement(requiredAsteroids: RequiredAsteroids){
        viewModelScope.launch(Dispatchers.IO) {

            when(requiredAsteroids){

                RequiredAsteroids.ALL -> {_asteroids.postValue(repository.getAll())}
                RequiredAsteroids.WEEK -> {_asteroids.postValue(repository.getWeek())}
                RequiredAsteroids.TODAY -> {_asteroids.postValue(repository.getToday())}

            }
        }
    }



}