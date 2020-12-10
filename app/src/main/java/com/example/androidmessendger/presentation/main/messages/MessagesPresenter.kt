package com.example.androidmessendger.presentation.main.messages

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.androidmessendger.base.SubRX
import com.example.androidmessendger.domain.repositories.DialogsRepository
import com.example.androidmessendger.domain.repositories.UserRepository
import com.example.androidmessendger.domain.repositories.models.realm.MessageItem
import javax.inject.Inject

@InjectViewState
class MessagesPresenter : MvpPresenter<IMessagesView> {
    private val dialogsRepository: DialogsRepository
    private val userRepository: UserRepository

    @Inject
    constructor(dialogsRepository: DialogsRepository, userRepository: UserRepository) {
        this.dialogsRepository = dialogsRepository
        this.userRepository = userRepository
    }

    fun sendMessage(message: String, to: Int) {
        val user = userRepository.getUser()
        dialogsRepository.sendMessage(
            SubRX { message, e ->
                val messageItem = MessageItem()
                messageItem.id = message?.id!!
                messageItem.dialogId = message?.from!!
                messageItem.userId = user?.id!!
                messageItem.deliveredStatus = false
                messageItem.message = message?.message.toString()

                dialogsRepository.addMessageInDialog(messageItem, {})
            },
            message,
            to
        )
    }

    fun loadMessages(dialogId: Int) {
        viewState.lock()
        dialogsRepository.getMessagesForDialog({ messages ->
            messages.let {
                Log.d("222222", it.toString())
                viewState.bindMessages(it)
            }

        }, dialogId)
    }

    fun updateMessages(dialogId: Int) {
        dialogsRepository.updateMessages(SubRX { messages, e ->
            messages?.forEach {
                it.id?.let { itId ->
                    val messageItem = MessageItem()
                    Log.d("from", it.from.toString())
                    messageItem.id = itId
                    messageItem.dialogId = dialogId
                    messageItem.userId = it.from!!
                    messageItem.date = it.date.toString()
                    messageItem.deliveredStatus = it.delivered!!
                    messageItem.message = it.message

                    dialogsRepository.addMessageInDialog(messageItem, {})
                }
            }

        }, dialogId)
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

    }
}