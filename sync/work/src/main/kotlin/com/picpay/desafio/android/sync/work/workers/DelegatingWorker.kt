package com.picpay.desafio.android.sync.work.workers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import org.koin.core.Koin
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatform.getKoin
import kotlin.reflect.KClass

private const val WORKER_CLASS_NAME = "RouterWorkerDelegateClassName"

internal fun KClass<out CoroutineWorker>.delegatedData() =
    Data.Builder()
        .putString(WORKER_CLASS_NAME, qualifiedName)
        .build()


class DelegatingWorker(
    appContext: Context,
    workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams) {

    private val workerClassName =
        workerParams.inputData.getString(WORKER_CLASS_NAME) ?: ""

    private val delegateWorker: CoroutineWorker? by lazy {
        val workerClass = Class.forName(workerClassName).kotlin
        getKoin().getOrNull<ListenableWorker>(workerClass) {
            parametersOf(appContext, workerParams)
        } as? CoroutineWorker
    }
    override suspend fun doWork(): Result {
        return delegateWorker?.doWork()
            ?: Result.failure()
    }
}

class KoinWorkerFactory(
    private val koin: Koin
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? =
        runCatching {
            val workerClass = Class.forName(workerClassName).kotlin

            koin.getOrNull<ListenableWorker>(workerClass) {
                parametersOf(appContext, workerParameters)
            }
        }.getOrNull()
}