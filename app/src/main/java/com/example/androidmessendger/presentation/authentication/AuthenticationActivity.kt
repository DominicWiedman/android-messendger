package com.example.androidmessendger.presentation.authentication

import android.content.Intent
import android.os.Bundle
import com.example.androidmessendger.App
import com.example.androidmessendger.R
import com.example.androidmessendger.base.ABaseActivity
import com.example.androidmessendger.domain.repositories.local.UserStorage
import com.example.androidmessendger.presentation.authentication.auth.AuthFragment
import com.example.androidmessendger.presentation.authentication.loading.LoadingFragment
import com.example.androidmessendger.presentation.authentication.registration.RegistrationFragment

class AuthenticationActivity : ABaseActivity(), IAuthenticationRouter {
    companion object {
        private const val ARG_DROP_AUTHENTICATION = "ARG_DROP_AUTHENTICATION"

        fun show() {
            App.appContext.let {
                it.startActivity(Intent(it, AuthenticationActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    putExtra(ARG_DROP_AUTHENTICATION, true)
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        if (savedInstanceState != null) return

        if (intent.getBooleanExtra(ARG_DROP_AUTHENTICATION, false)) {
            UserStorage().dropAuthentication()
            showLogin()
            return
        }

        showLoading()
    }

    override fun showLoading() {
        replace(LoadingFragment())
    }

    override fun showLogin() {
        replace(AuthFragment())
    }

    override fun showRegistration() {
        replace(RegistrationFragment())
    }
}