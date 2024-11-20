package com.glinyanov.childstars.book.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.glinyanov.childstars.book.data.database.ChildStarsDatabase

actual class DatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<ChildStarsDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(ChildStarsDatabase.DB_NAME)

        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}