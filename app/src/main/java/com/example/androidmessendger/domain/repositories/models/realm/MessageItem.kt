package com.example.androidmessendger.domain.repositories.models.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class MessageItem : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var dialogId: Int = 0
    var userId: Int = 0
    var deliveredStatus: Boolean = false
    var message: String = ""
}