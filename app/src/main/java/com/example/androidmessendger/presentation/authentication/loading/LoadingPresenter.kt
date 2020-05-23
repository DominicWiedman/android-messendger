package com.example.androidmessendger.presentation.authentication.loading

import android.os.Handler
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.androidmessendger.domain.repositories.UserRepository
import com.example.androidmessendger.presentation.main.MainActivity
import javax.inject.Inject

@InjectViewState
class LoadingPresenter : MvpPresenter<ILoadingView> {
    private val userRepository: UserRepository

    @Inject
    constructor(userRepository: UserRepository) {
        this.userRepository = userRepository
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadStaticResources()
    }

    private fun loadStaticResources() {
        Handler().postDelayed({
            val token = userRepository.getToken()
            if (token != null) {
                MainActivity.show()
                return@postDelayed
            }
            viewState.showLogin()
        }, 500)
    }
}