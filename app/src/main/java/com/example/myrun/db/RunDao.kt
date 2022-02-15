package com.example.myrun.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RunDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)

    @Query("SELECT * FROM running_table ORDER BY timestamp DESC")
    fun getAllRunsSortedByDate(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY avgSpeed DESC")
    fun getAllRunsSortedByAvgSpeed(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY distance DESC")
    fun getAllRunsSortedByDistance(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY duration DESC")
    fun getAllRunsSortedByDuration(): LiveData<List<Run>>

    @Query("SELECT * FROM running_table ORDER BY caloriesBurned DESC")
    fun getAllRunsSortedByCaloriesBurned(): LiveData<List<Run>>

    @Query("SELECT AVG(avgSpeed) FROM running_table")
    fun getTotalAvgSpeed(): LiveData<Float>

    @Query("SELECT SUM(distance) FROM running_table")
    fun getTotalDistance(): LiveData<Int>

    @Query("SELECT SUM(duration) FROM running_table")
    fun getTotalTimeInMillis(): LiveData<Long>

    @Query("SELECT SUM(caloriesBurned) FROM running_table")
    fun getTotalCaloriesBurned(): LiveData<Int>
}