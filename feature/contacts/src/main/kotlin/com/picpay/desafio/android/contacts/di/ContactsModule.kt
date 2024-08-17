package com.picpay.desafio.android.contacts.di

import com.picpay.desafio.android.contacts.ContactsScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val contactsModule = module {
    viewModel {
        ContactsScreenViewModel(get())
    }
}