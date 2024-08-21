package com.picpay.desafio.android.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.picpay.desafio.android.datastore.PreferencesKeys.SYNC_HASH
import kotlinx.coroutines.flow.first

const val DATA_STORE = "app_preferences"
val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE)

class DataStoreRepository(
    private val context: Context
) {

    suspend fun getSyncHash(): String? =
         context.dataStore.data.first()[SYNC_HASH]

    suspend fun saveSyncHash(syncHash: String) =
        runCatching { context.dataStore.edit { it[SYNC_HASH] = syncHash } }
            .isSuccess
}