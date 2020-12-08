package com.example.androidmessendger.presentation.main.messages

import android.content.Context
import android.util.AttributeSet
import com.example.androidmessendger.R
import com.example.androidmessendger.base.ABaseView
import com.example.androidmessendger.domain.repositories.models.realm.MessageItem
import kotlinx.android.synthetic.main.message_item.view.*

class MessageView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ABaseView(context, attr, defStyleAttr), IMessageView {
    override fun getViewId(): Int = R.layout.message_item

    override fun bind(message: MessageItem) {
        userTitleMessage.text = message.userId.toString()
        messageText.text = message.message
        deliveredStatus.text = if (message.deliveredStatus) "Прочитано" else "Доставлено"
    }
}