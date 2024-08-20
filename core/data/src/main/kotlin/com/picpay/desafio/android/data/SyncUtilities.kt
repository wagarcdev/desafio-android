package com.picpay.desafio.android.data

interface Syncable {

    suspend fun sync(): Boolean
}