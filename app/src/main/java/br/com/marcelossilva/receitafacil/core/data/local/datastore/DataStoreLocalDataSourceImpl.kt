package br.com.marcelossilva.receitafacil.core.data.local.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import br.com.marcelossilva.receitafacil.core.domain.model.UserData
import br.com.marcelossilva.receitafacil.core.util.logging.logError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreLocalDataSourceImpl @Inject constructor(
    private val dataStorePreferences: DataStore<Preferences>
) : DataStoreLocalDataSource {

    private object PreferencesKeys {
        val TOKEN_KEY = stringPreferencesKey(name = "token_key")
        val USER_NAME_KEY = stringPreferencesKey(name = "user_name_key")
    }

    override fun getData(): Flow<UserData> {
        return dataStorePreferences.data.catch { error ->
            error.localizedMessage?.let {
                logError("DATA_STORE_ERROR", it)
                emit(emptyPreferences())
            }
        }.map { preferences ->
            val token = preferences[PreferencesKeys.TOKEN_KEY] ?: ""
            val userName = preferences[PreferencesKeys.USER_NAME_KEY] ?: ""
            UserData(
                token = token,
                userName = userName
            )
        }
    }

    override suspend fun saveData(token: String, userName: String) {
        dataStorePreferences.edit { preferences ->
            preferences[PreferencesKeys.TOKEN_KEY] = token
            preferences[PreferencesKeys.USER_NAME_KEY] = userName
        }
    }

    override suspend fun clearAll() {
        dataStorePreferences.edit { preferences ->
            preferences.clear()
        }
    }
}