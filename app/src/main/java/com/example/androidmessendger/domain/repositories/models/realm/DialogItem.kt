package com.example.androidmessendger.domain.repositories.models.realm

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class DialogItem : RealmObject() {

    @PrimaryKey
    var id: Int = 0
    var userId: Int = 0
    var userName: String? = null
    var lastMessage: String? = null

}
