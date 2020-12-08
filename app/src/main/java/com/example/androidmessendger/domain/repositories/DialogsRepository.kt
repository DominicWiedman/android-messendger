package com.example.androidmessendger.domain.repositories

import com.example.androidmessendger.base.SubRX
import com.example.androidmessendger.base.standardSubscribeIO
import com.example.androidmessendger.domain.repositories.local.DialogsStorage
import com.example.androidmessendger.domain.repositories.local.UserStorage
import com.example.androidmessendger.domain.repositories.models.realm.DialogItem
import com.example.androidmessendger.domain.repositories.models.realm.MessageItem
import com.example.androidmessendger.domain.repositories.models.rest.Message
import com.example.androidmessendger.domain.repositories.models.rest.Token
import com.example.androidmessendger.domain.repositories.models.rest.User
import com.example.androidmessendger.domain.repositories.rest.api.DialogRestApi
import java.util.*
import javax.inject.Inject

class DialogsRepository {
    private val rest: DialogRestApi
    private val storage: DialogsStorage
    private val token: Token

    @Inject
    constructor(storage: DialogsStorage, rest: DialogRestApi, storageUser: UserStorage) {
        this.rest = rest
        this.storage = storage
        this.token = storageUser.getToken()!!
    }

    fun updateMessages(observer: SubRX<List<Message>>, from: Int) {
//        rest.getMessages(accessTokenRealm = token.access, from = from)
//            .standardSubscribeIO(observer)
    }

    fun getMessages(observer: SubRX<List<Message>>, from: Int) {
        rest.getMessages(accessTokenRealm = token.access, from = from)
            .standardSubscribeIO(observer)
    }

    fun sendMessage(observer: SubRX<Message>, message: String, to: Int) {
        rest.sendMessage(accessTokenRealm = token.access, message = message, to = to)
            .standardSubscribeIO(observer)
    }

    fun getNewMessages(observer: SubRX<List<Message>>) {
        rest.getNewMessages(accessTokenRealm = token.access)
            .standardSubscribeIO(observer)
    }

    fun loadAllDialogs(returnDialogs: (data: List<DialogItem>) -> Unit) =
        storage.loadAllDialogs(returnDialogs)

    fun createNewDialog(dialog: DialogItem, returnDialogs: (data: DialogItem) -> Unit) =
        storage.createNewDialog(dialog, returnDialogs)

    fun addMessageInDialog(message: MessageItem, returnMessage: (data: MessageItem) -> Unit) =
        storage.addMessageInDialog(message, returnMessage)

    fun getMessagesForDialog(returnMessage: (data: List<MessageItem>) -> Unit, dialogId: Int) =
        storage.getMessagesForDialog(returnMessage, dialogId)

}