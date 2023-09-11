package com.example.asteroidradar.api

import com.example.asteroidradar.Constants
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory



   private val moshi = Moshi.Builder()
       .add(KotlinJsonAdapterFactory())
       .build()

   private val interceptor = HttpLoggingInterceptor().apply { level =  HttpLoggingInterceptor.Level.BODY }

   private val client = OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .build()

   private val retrofit = Retrofit.Builder().addConverterFactory(ScalarsConverterFactory.create())
       .addConverterFactory(MoshiConverterFactory.create(moshi))
       .addCallAdapterFactory(CoroutineCallAdapterFactory())
       .baseUrl(Constants.BASE_URL)
       .client(client)
       .build()


   object Api{
       val service : ApiService by lazy {
           retrofit.create(ApiService::class.java)
       }
}

