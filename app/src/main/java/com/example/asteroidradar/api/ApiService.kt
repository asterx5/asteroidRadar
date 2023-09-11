package com.example.asteroidradar.api

import com.example.asteroidradar.BuildConfig
import com.example.asteroidradar.Constants
import com.example.asteroidradar.PictureOfDay
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {


    @GET(Constants.PICTURE_OF_DAY_URL)
    suspend fun getImageOfTheDay(@Query(Constants.API_KEY_URL) apiKey : String = BuildConfig.API_KEY): Response<PictureOfDay>



    @GET(Constants.ASTEROIDS_ENDPOINT)
    suspend fun getAsteroidsAsync(
        @Query(Constants.START_DATE) startDate : String = getNextSevenDaysFormattedDates()[0],
        @Query(Constants.END_DATE) endDate : String = getNextSevenDaysFormattedDates()[7],
        @Query(Constants.API_KEY_URL) apiKey: String = BuildConfig.API_KEY
    ) : Response<String>
}