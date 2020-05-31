package com.example.androidmessendger.domain.repositories

import android.util.Log
import com.example.androidmessendger.base.SubRX
import com.example.androidmessendger.domain.repositories.local.DialogsStorage
import com.example.androidmessendger.domain.repositories.local.UserStorage
import com.example.androidmessendger.domain.repositories.models.realm.DialogItem
import com.example.androidmessendger.domain.repositories.models.rest.Token
import com.example.androidmessendger.domain.repositories.rest.api.DialogRestApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
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
        rest.getUsers(accessTokenRealm =  token.access).observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                result -> Log.d("users", result.toString())
            }, { error -> error.printStackTrace()})
    }
}