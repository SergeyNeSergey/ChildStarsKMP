package com.glinyanov.childstars.core.data.local

import android.content.Context
import okio.Path
import okio.Path.Companion.toPath

internal actual interface DataStorePathProvider {
    actual fun providePath(): Path
}

internal class AndroidDataStorePathProvider(private val context: Context) : DataStorePathProvider {
    override fun providePath(): Path {
        return context
            .filesDir
            .resolve(DataStoreFactory.DATA_STORE_FILE_NAME)
            .absolutePath
            .toPath()
    }
}