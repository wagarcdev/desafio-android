package com.picpay.desafio.android.contacts.di

import com.picpay.desafio.android.contacts.ContactsScreenViewModel
import com.picpay.desafio.android.contacts.datasource.UsersRepositoryImpl
import com.picpay.desafio.android.contacts.datasource.remote.UserService
import com.picpay.desafio.android.contacts.datasource.repository.UsersRepository
import com.picpay.desafio.android.contacts.datasource.usecase.GetUsersUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val featureContactsModule = module {

    viewModel { ContactsScreenViewModel(get()) }

    single<UserService> { get<Retrofit>().create(UserService::class.java) }

    factory { GetUsersUseCase(get()) }

    single<UsersRepository>{ UsersRepositoryImpl(get()) }
}