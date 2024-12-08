package com.kaliostech.runmaster.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import java.io.ByteArrayOutputStream
import java.util.Date

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromBitmap(bmp:Bitmap):ByteArray{
        val outputStrem = ByteArrayOutputStream()
        bmp.compress(Bitmap.CompressFormat.PNG,100,outputStrem)
        return outputStrem.toByteArray()
    }
    @TypeConverter
    fun toBitmap(bytes:ByteArray):Bitmap{
        return BitmapFactory.decodeByteArray(bytes,0,bytes.size)

    }
}
