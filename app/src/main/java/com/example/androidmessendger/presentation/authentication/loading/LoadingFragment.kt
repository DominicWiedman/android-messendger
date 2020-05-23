package com.example.androidmessendger.presentation.authentication.loading

import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.androidmessendger.App
import com.example.androidmessendger.R
import com.example.androidmessendger.base.ABaseFragment
import com.example.androidmessendger.presentation.authentication.IAuthenticationRouter
import javax.inject.Inject

class LoadingFragment : ABaseFragment(), ILoadingView {

    @Inject
    @InjectPresenter
    lateinit var presenter: LoadingPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun getViewId(): Int = R.layout.fragment_loading

    override fun showLogin() {
        activity?.let {
            if (it is IAuthenticationRouter) {
                it.showLogin()
            }
        }
    }

    override fun lock() {
        TODO("Not yet implemented")
    }

    override fun unlock() {
        TODO("Not yet implemented")
    }

    override fun onSuccess(message: String) {
        TODO("Not yet implemented")
    }

    override fun onError(message: String) {
        TODO("Not yet implemented")
    }
}
