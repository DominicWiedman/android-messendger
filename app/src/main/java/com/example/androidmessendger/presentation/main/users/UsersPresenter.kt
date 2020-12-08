package com.example.androidmessendger.presentation.main.users

import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.androidmessendger.base.SubRX
import com.example.androidmessendger.domain.repositories.DialogsRepository
import com.example.androidmessendger.domain.repositories.UserRepository
import com.example.androidmessendger.domain.repositories.models.realm.DialogItem
import com.example.androidmessendger.domain.repositories.models.rest.User
import com.example.androidmessendger.domain.repositories.models.toRealm
import javax.inject.Inject

@InjectViewState
class UsersPresenter : MvpPresenter<IUsersView> {

    private val dialogsRepository: DialogsRepository
    private val userRepository: UserRepository

    @Inject
    constructor(dialogsRepository: DialogsRepository, userRepository: UserRepository) {
        this.dialogsRepository = dialogsRepository
        this.userRepository = userRepository
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        userRepository.getUsers(SubRX {users, e ->
            users?.let {
//                var usersList = users.map { user -> User(user).toRealm() }
                viewState.bindUsers(it)
                Log.d("users", it.toString())
            }
            e?.let {
                viewState.onError(e.localizedMessage)
                return@SubRX
            }
        })
    }


    fun createNewDialog(user: User) {
        viewState.lock()
        val dialog = DialogItem()
        user.let {
            dialog.id = it.id!!
            dialog.userId = it.id
            dialog.userName = it.login
        }

        dialogsRepository.createNewDialog(dialog) { returnRealm ->

        }
        viewState.unlock()
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