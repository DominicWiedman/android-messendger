package com.example.androidmessendger.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.example.androidmessendger.App
import com.example.androidmessendger.R
import com.example.androidmessendger.base.ABaseActivity
import com.example.androidmessendger.presentation.main.dialogs.DialogsFragment
import com.example.androidmessendger.presentation.main.messages.MessagesFragment
import com.example.androidmessendger.presentation.main.users.UsersFragment
import com.example.androidmessendger.services.MessageService

class MainActivity : ABaseActivity(), IMainActivity {


    companion object {
        fun show() {
            App.appContext.let {
                it.startActivity(Intent(it, MainActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                })
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)

        val serviceClass = MessageService::class.java
        val intent = Intent(this, serviceClass)

        startService(intent)


        if (savedInstanceState != null)
            return
        replace(DialogsFragment())

    }

    override fun updateDialogs() {
        DialogsFragment().presenter.updateDialogs()
    }

    override fun showUsers() {
        replace(UsersFragment())
    }

    override fun showDialogs() {
        replace(DialogsFragment())
    }
}
