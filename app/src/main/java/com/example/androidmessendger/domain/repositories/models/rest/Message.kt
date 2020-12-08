package com.example.androidmessendger.domain.repositories.models.rest

import java.util.*

data class Message (
    val id: Int? = null,
    val from: Int? = null,
    val to: Int? = null,
    val message: String = "",
    val date: String? = null,
    val delivered: Boolean? = null
)
