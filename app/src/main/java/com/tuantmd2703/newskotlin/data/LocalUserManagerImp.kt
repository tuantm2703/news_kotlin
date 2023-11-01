package com.tuantmd2703.newskotlin.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.tuantmd2703.newskotlin.domain.manager.LocalUserManager
import com.tuantmd2703.newskotlin.utils.AppConst.appEntry
import com.tuantmd2703.newskotlin.utils.AppConst.userSetting
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserManagerImp(private val context: Context) : LocalUserManager {
    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map { preferences ->
            preferences[PreferencesKeys.APP_ENTRY] ?: false
        }
    }
}

private object PreferencesKeys {
    val APP_ENTRY = booleanPreferencesKey(appEntry)
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = userSetting)
