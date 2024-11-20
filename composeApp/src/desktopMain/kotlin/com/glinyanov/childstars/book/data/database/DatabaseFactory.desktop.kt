package com.glinyanov.childstars.book.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.glinyanov.childstars.book.data.database.ChildStarsDatabase
import java.io.File

actual class DatabaseFactory {
    actual fun create(): RoomDatabase.Builder<ChildStarsDatabase> {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "ChildStars")
            os.contains("mac") -> File(userHome, "Library/Application Support/ChildStars")
            else -> File(userHome, ".local/share/ChildStars")
        }

        if(!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        val dbFile = File(appDataDir, ChildStarsDatabase.DB_NAME)
        return Room.databaseBuilder(dbFile.absolutePath)
    }
}