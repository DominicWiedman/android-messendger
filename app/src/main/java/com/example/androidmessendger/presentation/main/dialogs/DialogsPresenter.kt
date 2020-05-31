package com.example.androidmessendger.presentation.main.dialogs

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.androidmessendger.domain.repositories.DialogsRepository
import javax.inject.Inject

@InjectViewState
class DialogsPresenter : MvpPresenter<IDialogsView> {

    private val dialogsRepository: DialogsRepository

    @Inject
    constructor(dialogsRepository: DialogsRepository) {
        this.dialogsRepository = dialogsRepository
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
//        dialogsRepository.getUsers(SubRX { users, e ->
//            users?.let {viewState.bindDialogs(users)}
//            e?.printStackTrace()
//        })
    }

//    fun getUsers() {
//        viewState.lock()
//        dialogsRepository.getUsers(SubRX { _, e ->
//            viewState.unlock()
//
//            if (e != null) {
//                e.printStackTrace()
//                viewState.onError(e.localizedMessage)
//                return@SubRX
//            }
//        })
//    }
}