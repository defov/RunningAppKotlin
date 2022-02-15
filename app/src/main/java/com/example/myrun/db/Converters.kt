package com.example.myrun.db

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream

class Converters {

    @TypeConverter
    fun toBitmap(bytes: ByteArray) : Bitmap {
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }

    @TypeConverter
    fun fromBitmap(bmp: Bitmap): ByteArray {
        val bitmap = Bitmap.createScaledBitmap(
            bmp,
            1000,
            1000,
            false
        )
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 60, outputStream)
        return outputStream.toByteArray()
    }
}