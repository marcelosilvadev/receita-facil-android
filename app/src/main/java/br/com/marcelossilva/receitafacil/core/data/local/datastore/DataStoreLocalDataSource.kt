package br.com.marcelossilva.receitafacil.core.data.local.datastore

import br.com.marcelossilva.receitafacil.core.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface DataStoreLocalDataSource {
    fun getData(): Flow<UserData>
    suspend fun saveData(token: String, userName: String)
    suspend fun clearAll()
}