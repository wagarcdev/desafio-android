package com.picpay.desafio.android.contacts.di

import com.picpay.desafio.android.contacts.datasource.local.UserLocalDataSourceImpl
import com.picpay.desafio.android.contacts.datasource.remote.UserRemoteDataSourceImpl
import com.picpay.desafio.android.contacts.datasource.usecase.di.featureContactsUseCasesModule
import com.picpay.desafio.android.contacts.viewmodel.ContactsScreenViewModel
import com.picpay.desafio.android.core.data.repository.UserLocalDataSource
import com.picpay.desafio.android.core.data.repository.UserRemoteDataSource
import com.picpay.desafio.android.core.data.repository.UsersRepository
import com.picpay.desafio.android.core.data.repository.impl.UsersRepositoryImpl
import com.picpay.desafio.android.network.services.UserService
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit

val featureContactsModule = module {

    viewModelOf(::ContactsScreenViewModel)

    single<UserService> { get<Retrofit>().create(UserService::class.java) }

    includes(featureContactsUseCasesModule)

    single<UserLocalDataSource> { UserLocalDataSourceImpl(get()) }

    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get()) }

    singleOf(::UsersRepositoryImpl) { bind<UsersRepository>() }
}