package com.example.androidmessendger.domain.repositories

import com.example.androidmessendger.base.standardSubscribeIO
import com.example.androidmessendger.domain.repositories.local.DialogsStorage
import com.example.androidmessendger.domain.repositories.local.UserStorage
import com.example.androidmessendger.domain.repositories.models.realm.DialogItem
import com.example.androidmessendger.domain.repositories.models.rest.Token
import com.example.androidmessendger.domain.repositories.models.rest.User
import com.example.androidmessendger.domain.repositories.rest.api.DialogRestApi
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

    fun loadAllDialogs() = storage.loadAllDialogs()

    fun createNewDialog(dialog: DialogItem) = storage.createNewDialog(dialog)

    fun getUsers() {
        rest.getUsers(token.access)
    }
}