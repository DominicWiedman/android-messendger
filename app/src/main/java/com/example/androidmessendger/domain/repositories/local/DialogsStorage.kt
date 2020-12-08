package com.example.androidmessendger.domain.repositories.local

import android.util.Log
import com.example.androidmessendger.base.SubRX
import com.example.androidmessendger.domain.repositories.models.realm.DialogItem
import com.example.androidmessendger.domain.repositories.models.realm.MessageItem
import com.example.androidmessendger.domain.repositories.models.rest.Message
import com.example.androidmessendger.domain.repositories.models.rest.User
import com.example.androidmessendger.domain.repositories.models.rest.Users
import io.realm.Realm
import io.realm.RealmResults
import java.util.*
import javax.inject.Inject

class DialogsStorage {

    @Inject
    constructor()

    fun addMessageInDialog(message: MessageItem, returnRealm: (data: MessageItem) -> Unit) {
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                message.let {
                    realm.copyToRealmOrUpdate(it)
                }
            }
        }
    }

    fun updateMessages(messages: List<MessageItem>) {
        Realm.getDefaultInstance().use {
            it.beginTransaction()
            messages.forEach { itMessage ->
                it.copyToRealmOrUpdate(itMessage)
            }
            it.commitTransaction()
        }
    }

    fun getMessagesForDialog(returnRealm: (data: List<MessageItem>) -> Unit, dialogId: Int) {
        Realm.getDefaultInstance().use {
            val messages = it.where(MessageItem::class.java).equalTo("dialogId", dialogId).findAll()
            val list = it.copyFromRealm(messages)
            returnRealm(list)
        }
    }

    fun loadAllDialogs(returnRealm: (data: List<DialogItem>) -> Unit) {
        Realm.getDefaultInstance().use {

            val dialogs = it.where(DialogItem::class.java).findAll()
            val list = it.copyFromRealm(dialogs)

            Log.d("222222222222222222223", list.toString())
            returnRealm(list)
        }
    }

    fun createNewDialog(dialogItem: DialogItem, returnDialogs: (data: DialogItem) -> Unit) {
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                dialogItem.let {
                    realm.copyToRealmOrUpdate(it)
                }
            }
        }
    }
}