package com.example.androidmessendger.presentation.main.dialogs

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidmessendger.base.ABaseAdapter
import com.example.androidmessendger.domain.repositories.models.realm.DialogItem

class DialogAdapter(private val onDialogClick: OnDialogClickListener) : ABaseAdapter<DialogItem, RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = DialogView(parent.context)
        view.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val view = holder.itemView
        val currentItem = data[position]
        if (view is IDialogView) {
            view.bind(currentItem, onDialogClick)
        }
    }
}

interface OnDialogClickListener {
    fun onDialogClick(dialog: DialogItem)
}