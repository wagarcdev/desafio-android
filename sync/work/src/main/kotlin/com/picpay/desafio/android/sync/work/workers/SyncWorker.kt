package com.picpay.desafio.android.sync.work.workers

import android.content.Context
import androidx.work.BackoffPolicy
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.OutOfQuotaPolicy
import androidx.work.WorkerParameters
import com.picpay.desafio.android.contacts.datasource.repository.UsersRepository
import com.picpay.desafio.android.sync.work.initializers.SyncConstraints
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext
import java.time.Duration

class SyncWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val ioDispatcher: CoroutineDispatcher,
    private val usersRepository: UsersRepository
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(ioDispatcher) {

        val syncedSuccessfully = awaitAll(
            async { usersRepository.sync() },
        ).all { it }


        if (syncedSuccessfully) Result.success()
        else Result.retry()
    }

    companion object {
        fun startUpSyncWork() = OneTimeWorkRequestBuilder<DelegatingWorker>()
            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setConstraints(SyncConstraints)
            .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, Duration.ofSeconds(15))
            .setInputData(SyncWorker::class.delegatedData())
            .build()
    }
}