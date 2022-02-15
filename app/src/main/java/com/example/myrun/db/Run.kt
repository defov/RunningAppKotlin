package com.example.myrun.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "running_table")
data class Run (
    var img: Bitmap? = null,
    var timestamp: Long = 0L,
    var avgSpeed: Float = 0f,
    var distance: Int = 0,
    var duration: Long = 0L,
    var caloriesBurned: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}