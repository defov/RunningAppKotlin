package com.example.myrun.repositories

import com.example.myrun.db.Run
import com.example.myrun.db.RunDao
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDao: RunDao
) {

    suspend fun insertRun(run: Run) = runDao.insertRun(run)

    suspend fun deleteRun(run: Run) = runDao.deleteRun(run)

    fun getAllRunsSortedByDate() = runDao.getAllRunsSortedByDate()

    fun getAllRunsSortedByAvgSpeed() = runDao.getAllRunsSortedByAvgSpeed()

    fun getAllRunsSortedByDistance() = runDao.getAllRunsSortedByDistance()

    fun getAllRunsSortedByDuration() = runDao.getAllRunsSortedByDuration()

    fun getAllRunsSortedByCaloriesBurned() = runDao.getAllRunsSortedByCaloriesBurned()

    fun getTotalAvgSpeed() = runDao.getTotalAvgSpeed()

    fun getTotalDistance() = runDao.getTotalDistance()

    fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis()

    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()

}