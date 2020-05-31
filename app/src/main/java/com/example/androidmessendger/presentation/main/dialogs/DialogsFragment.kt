package com.example.androidmessendger.presentation.main.dialogs

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.androidmessendger.App
import com.example.androidmessendger.R
import com.example.androidmessendger.base.ABaseAdapter
import com.example.androidmessendger.base.ABaseListFragment
import com.example.androidmessendger.domain.repositories.models.rest.User
import javax.inject.Inject

class DialogsFragment : ABaseListFragment<User, RecyclerView.ViewHolder>(), IDialogsView {


    override fun getViewId(): Int = R.layout.dialogs_fragment
    override fun getListId(): Int = R.id.dialogList

    @Inject
    @InjectPresenter
    lateinit var presenter: DialogsPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun inject() {
        App.appComponent.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun provideAdapter(): ABaseAdapter<User, RecyclerView.ViewHolder> {
        TODO("Not yet implemented")
    }
}