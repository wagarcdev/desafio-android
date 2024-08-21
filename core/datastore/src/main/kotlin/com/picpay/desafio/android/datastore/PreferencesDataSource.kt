package com.picpay.desafio.android.datastore

interface PreferencesDataSource {

    suspend fun getSyncHash(): String?

    suspend fun saveSyncHash(hash: String): Boolean
}