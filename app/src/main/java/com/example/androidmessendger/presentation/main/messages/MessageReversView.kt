package com.example.androidmessendger.presentation.main.messages

import android.content.Context
import android.util.AttributeSet
import com.example.androidmessendger.R
import com.example.androidmessendger.base.ABaseView
import com.example.androidmessendger.domain.repositories.models.realm.MessageItem
import kotlinx.android.synthetic.main.message_item_revers.view.*

class MessageReversView @JvmOverloads constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ABaseView(context, attr, defStyleAttr), IMessageReversView {
    override fun getViewId(): Int = R.layout.message_item_revers

    override fun bindRevers(message: MessageItem) {
        messageText.text = message.message
        deliveredStatus.text = if (message.deliveredStatus) "Прочитано" else "Доставлено"
    }
}