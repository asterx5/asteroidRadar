package com.example.asteroidradar

import android.os.Parcelable
import androidx.room.Entity
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize


data class PictureOfDay(@Json(name = "media_type") val mediaType: String,@Json(name = "title") val title: String,
                        val url: String,@Json(name = "explanation") val explanation : String)