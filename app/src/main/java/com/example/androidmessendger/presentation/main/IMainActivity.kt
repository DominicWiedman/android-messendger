package com.example.androidmessendger.presentation.main

interface IMainActivity {
    fun showUsers()
    fun showDialogs()
    fun updateDialogs()
    fun showMessages(dialogId: Int)
}