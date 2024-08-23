package com.picpay.desafio.android.sync.work.di

import com.picpay.desafio.android.data.sync.SyncManager
import com.picpay.desafio.android.sync.work.status.WorkManagerSyncManager
import com.picpay.desafio.android.sync.work.workers.SyncWorker
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val syncModule = module {

    single<CoroutineDispatcher> { Dispatchers.IO }

    singleOf(::WorkManagerSyncManager) { bind<SyncManager>() }
    workerOf(::SyncWorker)

}