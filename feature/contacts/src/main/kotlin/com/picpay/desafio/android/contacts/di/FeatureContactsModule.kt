package com.picpay.desafio.android.contacts.di

import com.picpay.desafio.android.contacts.ContactsScreenViewModel
import com.picpay.desafio.android.data.remote.UserService
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val featureContactsModule = module {

    viewModel { ContactsScreenViewModel(get()) }

    single<UserService> { get<Retrofit>().create(UserService::class.java) }

}