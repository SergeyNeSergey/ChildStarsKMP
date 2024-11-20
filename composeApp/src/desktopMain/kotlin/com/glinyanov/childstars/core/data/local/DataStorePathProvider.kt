package com.glinyanov.childstars.core.data.local

import okio.Path
import okio.Path.Companion.toPath
import java.io.File

internal actual interface DataStorePathProvider {
    actual fun providePath(): Path
}

internal class DesktopDataStorePathProvider : DataStorePathProvider {
    override fun providePath(): Path {
        val os = System.getProperty("os.name").lowercase()
        val userHome = System.getProperty("user.home")
        val appDataDir = when {
            os.contains("win") -> File(System.getenv("APPDATA"), "ChildStars")
            os.contains("mac") -> File(userHome, "Library/Application Support/ChildStars")
            else -> File(userHome, ".local/share/ChildStars")
        }

        if (!appDataDir.exists()) {
            appDataDir.mkdirs()
        }

        return File(appDataDir, DataStoreFactory.DATA_STORE_FILE_NAME).absolutePath.toPath()
    }
}