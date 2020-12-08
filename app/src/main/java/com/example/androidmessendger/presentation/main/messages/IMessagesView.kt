package com.example.androidmessendger.presentation.main.messages

import com.example.androidmessendger.base.IBaseView
import com.example.androidmessendger.domain.repositories.models.realm.MessageItem

interface IMessagesView: IBaseView {
    fun bindMessages(messages: List<MessageItem>)
}