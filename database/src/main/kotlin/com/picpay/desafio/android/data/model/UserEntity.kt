package com.picpay.desafio.android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.picpay.desafio.android.domain.model.UserModel

@Entity(tableName = "user_tbl")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val externalId: Int,
    val img: String,
    val name: String,
    val username: String
)

fun UserModel.toEntity() =
    UserEntity(
        externalId = externalId,
        img = img,
        name = name,
        username = username
    )

fun UserEntity.toModel() =
    UserModel(
        externalId = externalId,
        img = img,
        name = name,
        username = username
    )
