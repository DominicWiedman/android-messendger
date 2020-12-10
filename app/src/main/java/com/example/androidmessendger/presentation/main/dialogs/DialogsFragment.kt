package com.example.androidmessendger.presentation.main.dialogs

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.androidmessendger.App
import com.example.androidmessendger.R
import com.example.androidmessendger.base.ABaseAdapter
import com.example.androidmessendger.base.ABaseListFragment
import com.example.androidmessendger.domain.repositories.models.realm.DialogItem
import com.example.androidmessendger.domain.repositories.models.rest.User
import com.example.androidmessendger.presentation.main.IMainActivity
import kotlinx.android.synthetic.main.dialogs_fragment.*
import javax.inject.Inject

class DialogsFragment() : ABaseListFragment<DialogItem, RecyclerView.ViewHolder>(), IDialogsView,
    OnDialogClickListener {


    override fun getViewId(): Int = R.layout.dialogs_fragment
    override fun getListId(): Int = R.id.dialogList

    @Inject
    @InjectPresenter
    lateinit var presenter: DialogsPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private val adapter = DialogAdapter(this)
    override fun provideAdapter() = adapter

    override fun inject() {
        App.appComponent.inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createDialogButton.setOnClickListener {
            activity?.let {
                if (it is IMainActivity) {
                    it.showUsers()
                }
            }
        }
    }


    override fun bindDialogs(dialogs: List<DialogItem>) {
        adapter.data = dialogs.toMutableList()
    }

    override fun onDialogClick(dialog: DialogItem) {
        activity?.let {
            if (it is IMainActivity) {
                it.showMessages(dialog.id)
            }
        }
    }
}