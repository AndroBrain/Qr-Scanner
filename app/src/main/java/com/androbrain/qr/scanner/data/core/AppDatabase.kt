package com.androbrain.qr.scanner.data.core

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.androbrain.qr.scanner.data.url.local.UrlDao
import com.androbrain.qr.scanner.data.url.local.UrlEntity

@Database(
    entities = [UrlEntity::class],
    version = 1,
    exportSchema = true,
)
@TypeConverters(TimeAndDateConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun urlDao(): UrlDao
}
