package com.example.asteroidradar.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.asteroidradar.api.Api
import com.example.asteroidradar.api.getNextSevenDaysFormattedDates
import com.example.asteroidradar.api.parseAsteroidsJsonResult
import com.example.asteroidradar.model.Asteroid
import org.json.JSONObject

class Repository( database: AsteroidsDatabase) {



    private val dao = database.asteroidsDao





     fun getAll() : List<Asteroid>? = dao.getAllAsteroids()
     fun getWeek() : List<Asteroid>? = dao.getWeekAsteroids(getNextSevenDaysFormattedDates()[0])
     fun getToday() : List<Asteroid>? = dao.getTodayAsteroids(getNextSevenDaysFormattedDates()[0])

     private fun insertAsteroids(asteroids : ArrayList<Asteroid>){
        dao.insertAsteroids(*asteroids.toTypedArray())
    }

     fun deleteOldData() = dao.deleteOld()

     suspend fun getAsteroids(){

        try {
            val response = parseAsteroidsJsonResult(JSONObject(Api.service.getAsteroidsAsync().body()))
            insertAsteroids(response)




        }catch (e:Exception){
            Log.i("MyTag", e.message.toString())
        }
    }


}