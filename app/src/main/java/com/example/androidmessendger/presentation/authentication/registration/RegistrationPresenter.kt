package com.example.androidmessendger.presentation.authentication.registration

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.androidmessendger.base.SubRX
import com.example.androidmessendger.domain.repositories.UserRepository
import javax.inject.Inject

@InjectViewState
class RegistrationPresenter : MvpPresenter<IRegistrationView> {
    private val userRepository: UserRepository

    @Inject
    constructor(userRepository: UserRepository) {
        this.userRepository = userRepository
    }

    fun registration(login: String, pass: String) {
        viewState.lock()
        userRepository.registration(SubRX { _, e ->
            viewState.unlock()

            if (e != null) {
                e.printStackTrace()
                viewState.onError(e.localizedMessage)
                return@SubRX
            }

        }, login, pass)
    }
}
