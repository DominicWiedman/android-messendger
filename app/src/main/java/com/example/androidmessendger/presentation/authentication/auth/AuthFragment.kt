package com.example.androidmessendger.presentation.authentication.auth

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.androidmessendger.App
import com.example.androidmessendger.R
import com.example.androidmessendger.base.ABaseFragment
import com.example.androidmessendger.presentation.authentication.IAuthenticationRouter
import kotlinx.android.synthetic.main.login_fragment.*
import javax.inject.Inject


class AuthFragment : ABaseFragment(), IAuthView {

    @Inject
    @InjectPresenter
    lateinit var presenter: AuthPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun getViewId() = R.layout.login_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnLogin.setOnClickListener {

            val login = "${etLogin.text}"
            val password = "${etPassword.text}"

            if (login.isEmpty() || password.isEmpty()) {
                toast(R.string.error_login_passwd_undefined)
                return@setOnClickListener
            }

            presenter.auth(login, password)
        }

        btnToRegister.setOnClickListener {
            activity?.let {
                if (it is IAuthenticationRouter)
                    it.showRegistration()
            }
        }
    }

    override fun onSuccess() {
        TODO("Not yet implemented")
    }
}