package com.example.androidmessendger.domain.di.components

import com.example.androidmessendger.domain.di.modules.AppModule
import com.example.androidmessendger.domain.di.modules.NetModule
import com.example.androidmessendger.presentation.authentication.auth.AuthFragment
import com.example.androidmessendger.presentation.authentication.loading.LoadingFragment
import com.example.androidmessendger.presentation.authentication.registration.RegistrationFragment
import com.example.androidmessendger.presentation.main.users.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetModule::class
    ]
)

interface AppComponent {
    fun inject(target: AuthFragment)
    fun inject(target: RegistrationFragment)
    fun inject(target: LoadingFragment)
    fun inject(target: UsersFragment)
}