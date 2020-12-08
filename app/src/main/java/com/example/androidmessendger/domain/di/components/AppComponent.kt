package com.example.androidmessendger.domain.di.components

import com.example.androidmessendger.domain.di.modules.AppModule
import com.example.androidmessendger.domain.di.modules.NetModule
import com.example.androidmessendger.presentation.authentication.auth.AuthFragment
import com.example.androidmessendger.presentation.authentication.loading.LoadingFragment
import com.example.androidmessendger.presentation.authentication.registration.RegistrationFragment
import com.example.androidmessendger.presentation.main.dialogs.DialogsFragment
import com.example.androidmessendger.presentation.main.messages.MessagesFragment
import com.example.androidmessendger.presentation.main.users.UsersFragment
import com.example.androidmessendger.services.MessageService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
//        AppModule::class,
        MessageService::class,
        NetModule::class
    ]
)

interface AppComponent {
    fun inject(target: AuthFragment)
    fun inject(target: RegistrationFragment)
    fun inject(target: LoadingFragment)
    fun inject(target: UsersFragment)
    fun inject(target: DialogsFragment)
    fun inject(target: MessageService)
    fun inject(target: MessagesFragment)
}