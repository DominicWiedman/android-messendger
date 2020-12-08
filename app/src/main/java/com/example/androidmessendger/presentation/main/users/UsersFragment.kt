package com.example.androidmessendger.presentation.main.users

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.androidmessendger.App
import com.example.androidmessendger.R
import com.example.androidmessendger.R.id.dialogList
import com.example.androidmessendger.base.ABaseAdapter
import com.example.androidmessendger.base.ABaseFragment
import com.example.androidmessendger.base.ABaseListFragment
import com.example.androidmessendger.domain.repositories.models.rest.User
import com.example.androidmessendger.presentation.main.IMainActivity
import com.example.androidmessendger.presentation.main.MainActivity
import com.example.androidmessendger.presentation.main.dialogs.DialogsPresenter
import kotlinx.android.synthetic.main.users_fragment.*
import javax.inject.Inject

class UsersFragment : ABaseListFragment<User, RecyclerView.ViewHolder>(), IUsersView,
    OnUserClickListener {


    override fun getViewId(): Int = R.layout.users_fragment
    override fun getListId(): Int = R.id.usersList

    @Inject
    @InjectPresenter
    lateinit var presenter: UsersPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    private val adapter = UsersAdapter(this)
    override fun provideAdapter() = adapter

    override fun inject() {
        App.appComponent.inject(this)
    }


    override fun bindUsers(users: List<User>) {
        adapter.data = users.toMutableList()
    }

    override fun onUserClick(user: User) {
        Log.d("1111", user.login)
        presenter.createNewDialog(user)
        activity?.let {
            if (it is IMainActivity) {
                it.showDialogs()
            }
//            it.onBackPressed()
        }
//        activity.let {
//            if (it is IMainActivity) user.id?.let { it1 -> it.showDialogs(it1) }
//        }
    }

    override fun lock() {
    }

}