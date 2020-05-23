package com.example.androidmessendger.domain.di.modules

import com.example.androidmessendger.domain.repositories.local.UserStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideUserStorage() = UserStorage()
}
