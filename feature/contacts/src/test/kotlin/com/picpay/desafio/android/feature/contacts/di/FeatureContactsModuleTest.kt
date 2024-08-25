package com.picpay.desafio.android.feature.contacts.di

import com.picpay.desafio.android.core.data.image.ImageCompressor
import com.picpay.desafio.android.core.data.image.ImageDecoder
import com.picpay.desafio.android.core.data.image.ImageProcessor
import com.picpay.desafio.android.core.data.network.NetworkMonitor
import com.picpay.desafio.android.core.data.repository.UserLocalDataSource
import com.picpay.desafio.android.core.data.repository.UserRemoteDataSource
import com.picpay.desafio.android.core.data.repository.UsersRepository
import com.picpay.desafio.android.core.data.sync.SyncManager
import com.picpay.desafio.android.core.domain.usecase.LocalUsersFlowUseCase
import com.picpay.desafio.android.core.domain.usecase.SearchLocalUsersFlowUseCase
import com.picpay.desafio.android.network.services.UserService
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.verify.verify
import retrofit2.Retrofit

@OptIn(KoinExperimentalAPI::class)
class FeatureContactsModuleTest {

    @Test
    fun checkFeatureContactsKoinModule() {
        featureContactsModule.verify(
            extraTypes = listOf(
                Retrofit::class,
                UserService::class,
                LocalUsersFlowUseCase::class,
                SearchLocalUsersFlowUseCase::class,
                UserLocalDataSource::class,
                UserRemoteDataSource::class,
                UsersRepository::class,
                SyncManager::class,
                NetworkMonitor::class,
                ImageProcessor::class,
                ImageDecoder::class,
                ImageCompressor::class
            )
        )
    }
}