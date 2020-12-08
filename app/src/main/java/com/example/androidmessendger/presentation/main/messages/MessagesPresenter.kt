package com.example.androidmessendger.presentation.main.messages

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.androidmessendger.domain.repositories.DialogsRepository
import javax.inject.Inject

@InjectViewState
class MessagesPresenter : MvpPresenter<IMessagesView> {
    private val dialogsRepository: DialogsRepository

    @Inject
    constructor(dialogsRepository: DialogsRepository) {
        this.dialogsRepository = dialogsRepository
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.lock()
        dialogsRepository.getMessagesForDialog({ messages ->
            messages?.let {
                viewState.bindMessages(it)
            }

        }, 448)
    }
}