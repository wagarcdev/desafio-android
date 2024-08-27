package com.picpay.desafio.android.sync.work.di.test

import com.picpay.desafio.android.core.data.sync.SyncManager
import com.picpay.desafio.android.sync.work.test.FakeWorkManagerSyncManager
import org.koin.androidx.workmanager.dsl.workerOf
import com.picpay.desafio.android.sync.work.test.FakeSyncWorker

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val testingSyncModule = module {

    singleOf(::FakeWorkManagerSyncManager) { bind<SyncManager>() }

    workerOf(::FakeSyncWorker)



}