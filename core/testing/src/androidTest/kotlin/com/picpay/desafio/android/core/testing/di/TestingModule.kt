package com.picpay.desafio.android.core.testing.di

import com.picpay.desafio.android.core.data.di.test.testingDataModule
import org.koin.dsl.module

val testingModules = module {
    includes(testingDataModule)
}