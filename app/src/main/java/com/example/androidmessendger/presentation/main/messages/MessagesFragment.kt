package com.example.androidmessendger.presentation.main.messages

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.androidmessendger.App
import com.example.androidmessendger.R
import com.example.androidmessendger.base.ABaseAdapter
import com.example.androidmessendger.base.ABaseListFragment
import com.example.androidmessendger.domain.repositories.models.realm.MessageItem
import javax.inject.Inject

class MessagesFragment(dialogId: Int) : ABaseListFragment<MessageItem, RecyclerView.ViewHolder>(),
    IMessagesView {

    override fun getViewId(): Int = R.layout.dialog_fragment
    override fun getListId(): Int = R.id.messageList

    @Inject
    @InjectPresenter
    lateinit var presenter: MessagesPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private val adapter = MessageAdapter()
    override fun provideAdapter() = adapter

    override fun inject() {
        App.appComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun bindMessages(messages: List<MessageItem>) {
        adapter.data = messages.toMutableList()
    }

}