package com.example.androidmessendger.presentation.main.users

import com.example.androidmessendger.base.IBaseView
import com.example.androidmessendger.domain.repositories.models.rest.User

interface IUsersView : IBaseView {
    fun bindUsers(users: List<User>)
}