package com.glinyanov.childstars.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences

internal object DataStoreFactory {
    const val DATA_STORE_FILE_NAME = "prefs.preferences_pb"

    fun create(pathProvider: DataStorePathProvider): DataStore<Preferences> {
        return PreferenceDataStoreFactory.createWithPath(produceFile = pathProvider::providePath)
    }
}