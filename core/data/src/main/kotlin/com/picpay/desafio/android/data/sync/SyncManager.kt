package com.picpay.desafio.android.data.sync

import kotlinx.coroutines.flow.Flow

interface SyncManager {
    val isSyncing: Flow<Boolean>
//    fun requestSync()
}