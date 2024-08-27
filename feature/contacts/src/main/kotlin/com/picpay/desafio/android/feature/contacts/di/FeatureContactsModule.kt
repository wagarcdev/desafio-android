package com.picpay.desafio.android.feature.contacts.di

import com.picpay.desafio.android.core.network.services.UserService
import com.picpay.desafio.android.feature.contacts.viewmodel.ContactsScreenViewModel
import com.picpay.desafio.android.core.network.services.UserServiceImpl
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit

val featureContactsModule = module {

    viewModelOf(::ContactsScreenViewModel)

    single<UserService> { get<Retrofit>().create(UserServiceImpl::class.java) }

}