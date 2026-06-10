package com.example.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsRepository(private val context: Context) {

    companion object {
        val SOUND_ON = booleanPreferencesKey("sound_on")
        val VIBRATION_ON = booleanPreferencesKey("vibration_on")
        val DARK_MODE = booleanPreferencesKey("dark_mode")
    }

    val soundOnFlow: Flow<Boolean> = context.dataStore.data.map { it[SOUND_ON] ?: true }
    val vibrationOnFlow: Flow<Boolean> = context.dataStore.data.map { it[VIBRATION_ON] ?: true }
    val darkModeFlow: Flow<Boolean> = context.dataStore.data.map { it[DARK_MODE] ?: true }

    suspend fun setSoundOn(on: Boolean) {
        context.dataStore.edit { it[SOUND_ON] = on }
    }

    suspend fun setVibrationOn(on: Boolean) {
        context.dataStore.edit { it[VIBRATION_ON] = on }
    }

    suspend fun setDarkMode(on: Boolean) {
        context.dataStore.edit { it[DARK_MODE] = on }
    }
}
