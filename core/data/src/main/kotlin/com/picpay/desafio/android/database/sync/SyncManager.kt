package com.picpay.desafio.android.database.sync

import kotlinx.coroutines.flow.Flow

interface SyncManager {
    val isSyncing: Flow<Boolean>
//    fun requestSync()
}