package com.example.androidmessendger.domain.repositories.local

import com.example.androidmessendger.domain.repositories.models.realm.DialogItem
import io.realm.Realm
import javax.inject.Inject

class DialogsStorage {
    private var dialogs: List<DialogItem>? = null

    @Inject
    constructor()

    fun loadAllDialogs() {
        Realm.getDefaultInstance().use {
            this.dialogs = it.where(DialogItem::class.java).findAll()
        }
    }

    fun createNewDialog(dialogItem: DialogItem) {
        Realm.getDefaultInstance().use {
            it.executeTransaction { realm ->
                dialogItem.let { realm.copyToRealmOrUpdate(it) }
            }
        }
    }
}