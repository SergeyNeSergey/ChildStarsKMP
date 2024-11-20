package com.glinyanov.childstars.core.data.local

import kotlinx.cinterop.ExperimentalForeignApi
import okio.Path
import okio.Path.Companion.toPath
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

internal actual interface DataStorePathProvider {
    actual fun providePath(): Path
}

internal class IosDataStorePathProvider : DataStorePathProvider {
    @OptIn(ExperimentalForeignApi::class)
    override fun providePath(): Path {
        val directoryPath = requireNotNull(
            NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null
            )?.path
        )
        return (directoryPath + "/${DataStoreFactory.DATA_STORE_FILE_NAME}").toPath()
    }
}