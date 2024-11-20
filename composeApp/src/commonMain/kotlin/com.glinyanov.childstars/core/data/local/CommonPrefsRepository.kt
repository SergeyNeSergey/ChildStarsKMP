package com.glinyanov.childstars.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private const val USER_ID_KEY = "USER_ID_KEY"

interface CommonPrefsRepository {
    suspend fun getUserId(): Int?
    suspend fun clearUserId()
    suspend fun setUserId(id: Int)
}

internal class CommonPrefsRepositoryImpl(
    private val prefs: DataStore<Preferences>
): CommonPrefsRepository {

    override suspend fun getUserId(): Int? {
        return prefs.data.map { it[intPreferencesKey(USER_ID_KEY)] }.first()
    }
    override suspend fun clearUserId() {
        prefs.edit { dataStore -> dataStore.clear() }
    }

    override suspend fun setUserId(id: Int) {
        prefs.edit { dataStore -> dataStore[intPreferencesKey(USER_ID_KEY)] = id }
    }
}