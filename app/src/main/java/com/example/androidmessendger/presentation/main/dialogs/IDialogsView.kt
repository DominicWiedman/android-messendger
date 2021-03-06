package com.example.androidmessendger.presentation.main.dialogs

import com.example.androidmessendger.base.IBaseView
import com.example.androidmessendger.domain.repositories.models.realm.DialogItem

interface IDialogsView : IBaseView {
    fun bindDialogs(dialogs: List<DialogItem>)
}