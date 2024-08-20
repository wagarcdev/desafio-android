package com.picpay.desafio.android.sync.work.di

import android.content.Context
import androidx.work.WorkerParameters
import com.picpay.desafio.android.data.SyncManager
import com.picpay.desafio.android.sync.work.status.WorkManagerSyncManager
import com.picpay.desafio.android.sync.work.workers.DelegatingWorker
import com.picpay.desafio.android.sync.work.workers.KoinWorkerFactory
import com.picpay.desafio.android.sync.work.workers.SyncWorker
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val syncModule = module {

    single<CoroutineDispatcher> { Dispatchers.IO }

    single { KoinWorkerFactory(getKoin()) }


    single<SyncManager> { WorkManagerSyncManager(androidContext()) }


    factory { (appContext: Context, workerParams: WorkerParameters) ->
        SyncWorker(
            appContext = appContext,
            workerParams = workerParams,
            ioDispatcher = get(),
            usersRepository = get()
        )
    }

    factory { (appContext: Context, workerParams: WorkerParameters) ->
        DelegatingWorker(appContext, workerParams)
    }
}