package com.picpay.desafio.android.contacts.di

import com.picpay.desafio.android.contacts.ContactsScreenViewModel
import com.picpay.desafio.android.contacts.datasource.local.UserLocalDataSourceImpl
import com.picpay.desafio.android.contacts.datasource.remote.UserRemoteDataSourceImpl
import com.picpay.desafio.android.contacts.datasource.remote.UserService
import com.picpay.desafio.android.contacts.datasource.repository.UserLocalDataSource
import com.picpay.desafio.android.contacts.datasource.repository.UserRemoteDataSource
import com.picpay.desafio.android.contacts.datasource.repository.UsersRepository
import com.picpay.desafio.android.contacts.datasource.repository.impl.UsersRepositoryImpl
import com.picpay.desafio.android.contacts.datasource.usecase.di.featureContactsUseCasesModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val featureContactsModule = module {

    viewModel { ContactsScreenViewModel(get(), get(), get()) }

    single<UserService> { get<Retrofit>().create(UserService::class.java) }

    includes(featureContactsUseCasesModule)

    single<UserLocalDataSource> { UserLocalDataSourceImpl(get()) }

    single<UserRemoteDataSource> { UserRemoteDataSourceImpl(get()) }

    single<UsersRepository>{ UsersRepositoryImpl(get(), get()) }
}

