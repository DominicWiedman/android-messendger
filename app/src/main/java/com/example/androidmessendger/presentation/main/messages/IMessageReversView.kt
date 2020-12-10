package com.example.androidmessendger.presentation.main.messages

import com.example.androidmessendger.domain.repositories.models.realm.MessageItem

interface IMessageReversView {
    fun bindRevers(message: MessageItem)
}
