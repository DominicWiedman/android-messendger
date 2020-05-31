package com.example.androidmessendger.domain.repositories.models.rest

data class Users(
    val users: List<UserItem>
)

data class UserItem(
    val id: Int? = null,
    val login: String
)