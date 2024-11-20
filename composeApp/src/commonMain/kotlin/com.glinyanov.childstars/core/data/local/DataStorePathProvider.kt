package com.glinyanov.childstars.core.data.local

import okio.Path

internal expect interface DataStorePathProvider {
    fun providePath(): Path
}