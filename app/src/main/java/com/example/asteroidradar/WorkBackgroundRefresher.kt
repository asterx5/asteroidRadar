package com.example.asteroidradar

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.asteroidradar.database.Repository
import com.example.asteroidradar.database.getDatabase

class WorkBackgroundRefresher (appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params)
{

    companion object {
        const val WORK_NAME = "WorkBackgroundRefresher"
    }

    override suspend fun doWork(): Result {

        val database = getDatabase(applicationContext)
        val repository = Repository(database)

        return try {
            repository.deleteOldData()
            repository.getAsteroids()
            Result.success()
        }catch (e : Exception){
            Result.retry()
        }
    }

}