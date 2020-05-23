package com.example.androidmessendger

import android.app.Application
import android.content.Context
import com.example.androidmessendger.domain.di.components.AppComponent
import com.example.androidmessendger.domain.di.components.DaggerAppComponent
import com.example.androidmessendger.domain.repositories.local.Migration
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {
    companion object {

        lateinit var appContext: Context
        lateinit var appComponent: AppComponent
    }

    private fun initRealm() {

        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.setDefaultConfiguration(configuration)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
        appContext = applicationContext

        initRealm()
    }


}