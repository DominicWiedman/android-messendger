package com.example.androidmessendger.presentation.main.dialogs

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.androidmessendger.base.SubRX
import com.example.androidmessendger.domain.repositories.DialogsRepository
import javax.inject.Inject

@InjectViewState
class DialogsPresenter : MvpPresenter<IDialogsView> {

    private val dialogsRepository: DialogsRepository

    @Inject
    constructor(dialogsRepository: DialogsRepository) {
        this.dialogsRepository = dialogsRepository
    }

    fun updateDialogs() {
        viewState.lock()
        dialogsRepository.loadAllDialogs { dialogs ->
            viewState.bindDialogs(dialogs)
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        updateDialogs()

//        dialogsRepository.getNewMessages(SubRX { messages, e ->
//            messages?.let {
//                Log.d("222222222222", it.toString())
//            }
//            e?.let {
//                viewState.onError(e.localizedMessage)
//                return@SubRX
//            }
//        })
    }
}