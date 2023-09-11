package com.example.asteroidradar

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.work.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit


class AsteroidApplication : Application() {


    private val constraints = Constraints
        .Builder()
        .setRequiresCharging(true)
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                setRequiresDeviceIdle(true)
            }
        }.build()


    override fun onCreate() {
        super.onCreate()
        Log.i("MyTag", "Application created")

        GlobalScope.launch(Dispatchers.IO) {
            val request = PeriodicWorkRequestBuilder<WorkBackgroundRefresher>(1,TimeUnit.DAYS)
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(applicationContext)
                .enqueueUniquePeriodicWork(WorkBackgroundRefresher.WORK_NAME , ExistingPeriodicWorkPolicy.KEEP , request)
        }
    }


}