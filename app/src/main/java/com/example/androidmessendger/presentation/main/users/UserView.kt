package com.example.androidmessendger.presentation.main.users

import android.content.Context
import android.util.AttributeSet
import com.example.androidmessendger.R
import com.example.androidmessendger.base.ABaseView
import com.example.androidmessendger.domain.repositories.models.rest.User
import kotlinx.android.synthetic.main.user_row.view.*

class UserView @JvmOverloads constructor(
    context: Context, attr: AttributeSet? = null, defStyleAttr: Int = 0
) : ABaseView(context, attr, defStyleAttr), ITypeView {
    override fun getViewId(): Int = R.layout.user_row

    override fun bind(data: User) {
        userTitle.text = data.login
    }
}
