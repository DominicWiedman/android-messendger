package com.example.androidmessendger.presentation.main.users

import com.example.androidmessendger.domain.repositories.models.rest.User

interface ITypeView {
    fun bind(data: User)
}
