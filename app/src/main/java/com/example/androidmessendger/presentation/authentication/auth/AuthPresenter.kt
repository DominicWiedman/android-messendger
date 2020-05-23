package com.example.androidmessendger.presentation.authentication.auth

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.androidmessendger.base.SubRX
import com.example.androidmessendger.domain.repositories.UserRepository
import javax.inject.Inject


@InjectViewState
class AuthPresenter : MvpPresenter<IAuthView> {
    private val userRepository: UserRepository

    @Inject
    constructor(userRepository: UserRepository) {
        this.userRepository = userRepository
    }

    fun auth(login: String, password: String) {
        userRepository.login(SubRX { _, e ->
            if (e != null) {
                e.printStackTrace()
                viewState.onError(e.localizedMessage)
                return@SubRX
            }
//            открытие основного активити
        }, login, password)
    }
}
