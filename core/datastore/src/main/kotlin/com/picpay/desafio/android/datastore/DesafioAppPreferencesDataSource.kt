package com.picpay.desafio.android.datastore

class DesafioAppPreferencesDataSource(
    private val repository: DataStoreRepository
): PreferencesDataSource {

    override suspend fun getSyncHash(): String? =
        repository.getSyncHash()

    override suspend fun saveSyncHash(hash: String): Boolean =
        repository.saveSyncHash(hash)
}