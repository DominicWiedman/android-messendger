package com.example.androidmessendger.presentation.main.dialogs

import com.example.androidmessendger.domain.repositories.models.realm.DialogItem

interface IDialogView {
    fun bind(dialog: DialogItem, onDialogClickListener: OnDialogClickListener)
}