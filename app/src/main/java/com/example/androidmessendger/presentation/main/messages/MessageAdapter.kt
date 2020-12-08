package com.example.androidmessendger.presentation.main.messages

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmessendger.base.ABaseAdapter
import com.example.androidmessendger.domain.repositories.models.realm.MessageItem

class MessageAdapter : ABaseAdapter<MessageItem, RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = MessageView(parent.context)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder.itemView
        val currentItem = data[position]
        if (view is IMessageView) {
            view.bind(currentItem)
        }
    }
}