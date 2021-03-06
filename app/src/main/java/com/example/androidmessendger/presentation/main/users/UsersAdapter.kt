package com.example.androidmessendger.presentation.main.users

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmessendger.base.ABaseAdapter
import com.example.androidmessendger.domain.repositories.models.rest.User
import kotlinx.android.synthetic.main.user_row.view.*

class UsersAdapter(private val onUserClick: OnUserClickListener) : ABaseAdapter<User, RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = UserView(parent.context)
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder.itemView
        val currentItem = data[position]
        if (view is IUserView) {
            view.bind(currentItem, onUserClick)
        }
    }
}

interface OnUserClickListener {
    fun onUserClick(user: User)
}