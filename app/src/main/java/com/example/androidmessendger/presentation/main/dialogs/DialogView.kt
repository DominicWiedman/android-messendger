package com.example.androidmessendger.presentation.main.dialogs

import android.content.Context
import android.util.AttributeSet
import com.example.androidmessendger.R
import com.example.androidmessendger.base.ABaseView
import com.example.androidmessendger.domain.repositories.models.realm.DialogItem
import com.example.androidmessendger.domain.repositories.models.rest.User
import kotlinx.android.synthetic.main.dialog_row.view.*
import kotlinx.android.synthetic.main.user_row.view.*

class DialogView @JvmOverloads constructor(
    context: Context, attr: AttributeSet? = null, defStyleAttr: Int = 0
) : ABaseView(context, attr, defStyleAttr), IDialogView {

    override fun getViewId(): Int = R.layout.dialog_row

    override fun bind(dialog: DialogItem, onDialogClickListener: OnDialogClickListener) {
        userTitleDialog.text = dialog.userName
        dialogLastMessage.text = dialog.lastMessage

        setOnClickListener {
            onDialogClickListener.onDialogClick(dialog)
        }
    }
}
