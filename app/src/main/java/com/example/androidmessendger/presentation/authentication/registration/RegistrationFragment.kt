package com.example.androidmessendger.presentation.authentication.registration

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.androidmessendger.App
import com.example.androidmessendger.R
import com.example.androidmessendger.base.ABaseFragment
import kotlinx.android.synthetic.main.register_fragment.*
import javax.inject.Inject

class RegistrationFragment : ABaseFragment(), IRegistrationView {

    @Inject
    @InjectPresenter
    lateinit var presenter: RegistrationPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun getViewId(): Int = R.layout.register_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnRegister.setOnClickListener {
            presenter.registration("${etLogin.text}", "${etPassword.text}")
        }
    }

}