package com.picpay.desafio.android.datastore

interface DataStoreRepository {

    suspend fun getSyncHash(): String?

    suspend fun saveSyncHash(syncHash: String): Boolean

}
