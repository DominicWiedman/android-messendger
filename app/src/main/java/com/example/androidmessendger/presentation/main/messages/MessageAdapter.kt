package com.example.androidmessendger.presentation.main.messages

import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmessendger.base.ABaseAdapter
import com.example.androidmessendger.domain.repositories.models.realm.MessageItem

class MessageAdapter(private var dialogId: Int) :
    ABaseAdapter<MessageItem, RecyclerView.ViewHolder>() {

    open class ViewHolder1(itemView1: View) : RecyclerView.ViewHolder(itemView1)
    open class ViewHolder2(itemView2: View) : RecyclerView.ViewHolder(itemView2)

    override fun getItemViewType(position: Int): Int {
        Log.d("userId", data[position].userId.toString())
        return if (data[position].userId == dialogId) 1 else 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = MessageView(parent.context)
        val viewRevers: View = MessageReversView(parent.context)
        return when (viewType) {
            1 -> object : ViewHolder1(view) {}
            2 -> object : ViewHolder2(viewRevers) {}
            else -> object : ViewHolder2(viewRevers) {}
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = data[position]
        Log.d("111111", holder.itemViewType.toString())

        when (holder.itemViewType) {
            1 -> {
                val view = ViewHolder1(holder.itemView).itemView
                if (view is IMessageView) {
                    view.bind(currentItem)
                }

            }
            2 -> {
                val viewRevers = ViewHolder2(holder.itemView).itemView
                if (viewRevers is IMessageReversView) {
                    viewRevers.bindRevers(currentItem)
                }
            }
        }


    }
}