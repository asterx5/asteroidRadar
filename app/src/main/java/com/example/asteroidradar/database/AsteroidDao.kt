package com.example.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.example.asteroidradar.model.Asteroid


@Dao
interface AsteroidDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAsteroids(vararg asteroid: Asteroid)

    @Query("SELECT * FROM asteroid_table ORDER BY close_approach_date ASC")
    fun getAllAsteroids() : List<Asteroid>?

    @Query("SELECT * FROM asteroid_table WHERE close_approach_date = :date ORDER BY close_approach_date ASC")
    fun getTodayAsteroids(date : String) : List<Asteroid>?

    // this returns all days of the week except today, I am not sure that's was what is meant but if not we would replace any use of this with the getAllAsteroids()
    @Query("SELECT * FROM asteroid_table WHERE close_approach_date != :date ORDER BY close_approach_date ASC")
    fun getWeekAsteroids(date : String) : List<Asteroid>?

    @Query("DELETE FROM asteroid_table")
    fun deleteOld()

}