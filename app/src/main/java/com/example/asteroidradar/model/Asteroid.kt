package com.example.asteroidradar.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "asteroid_table")
data class Asteroid(
                    @PrimaryKey(autoGenerate = false)
                    @ColumnInfo(name = "asteroid_id")
                    var id: Long,
                    @ColumnInfo(name = "codename")
                    var codename: String,
                    @ColumnInfo(name = "close_approach_date")
                    var closeApproachDate: String,
                    @ColumnInfo(name = "absolute_magnitude")
                    var absoluteMagnitude: Double,
                    @ColumnInfo(name = "estimated_diameter")
                    var estimatedDiameter: Double,
                    @ColumnInfo(name = "relative_velocity")
                    var relativeVelocity: Double,
                    @ColumnInfo(name = "distance_from_earth")
                    var distanceFromEarth: Double,
                    @ColumnInfo(name = "is_potentially_hazardous")
                    var isPotentiallyHazardous: Boolean) : Parcelable