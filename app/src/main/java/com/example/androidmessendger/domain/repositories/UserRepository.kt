package com.example.androidmessendger.domain.repositories

import android.os.SystemClock
import com.example.androidmessendger.base.SubRX
import com.example.androidmessendger.base.standardSubscribeIO
import com.example.androidmessendger.domain.repositories.local.UserStorage
import com.example.androidmessendger.domain.repositories.models.rest.Token
import com.example.androidmessendger.domain.repositories.models.rest.User
import com.example.androidmessendger.domain.repositories.rest.api.UserRestApi
import java.net.HttpURLConnection
import javax.inject.Inject

class UserRepository {
    private val storage: UserStorage
    private val rest: UserRestApi
    private var token: Token? = null

    @Inject
    constructor(storage: UserStorage, rest: UserRestApi) {
        this.storage = storage
        this.rest = rest
        this.token = storage.getToken()
    }

    fun registration(observer: SubRX<User>, login: String, pass: String) {
        rest.registration(login, pass)
            .doOnNext { storage.save(it) }
            .standardSubscribeIO(observer)
    }

    fun login(observer: SubRX<User>, login: String, pass: String) {

        rest.login(login, pass)
            .doOnNext { storage.save(it) }
            .standardSubscribeIO(observer)
    }

    fun logout() = storage.dropAuthentication()

    fun getUser(): User? {
        return storage.getUser()
    }

    fun getUsers(observer: SubRX<List<User>>) {
        token.let {
            if (it is Token) {
                rest.getUsers(accessTokenRealm = it.access).standardSubscribeIO(observer)
            }
        }
    }

    fun getToken() = storage.getToken()
    fun refreshToken(
        token: Token,
        onRetry: (Int) -> Boolean = { it == HttpURLConnection.HTTP_UNAUTHORIZED }
    ): Token? {

        val response = rest.refreshToken(token.refresh).execute()
        response.body()?.let {
            storage.save(it)
            return it
        }

        if (onRetry(response.code())) {
            SystemClock.sleep(500)
            return refreshToken(token)
        }

        return null
    }
}
