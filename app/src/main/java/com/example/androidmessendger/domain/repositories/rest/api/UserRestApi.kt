package com.example.androidmessendger.domain.repositories.rest.api

import com.example.androidmessendger.base.ABaseRestApi
import com.example.androidmessendger.base.IRestClient
import com.example.androidmessendger.domain.di.modules.NetModule
import com.example.androidmessendger.domain.repositories.models.rest.User
import com.example.androidmessendger.domain.repositories.rest.service.IUserRestApiService
import javax.inject.Inject
import javax.inject.Named

class UserRestApi : ABaseRestApi<IUserRestApiService> {

    @Inject
    constructor(@Named(NetModule.NAME_AUTH_REST_CLIENT) client: IRestClient) : super(client)

    fun registration(login: String, password: String) = service.registration(
        User(
            login = login,
            password = password
        )
    )


    fun login(login: String, password: String) = service.login(
        User(
            login = login,
            password = password
        )
    )


    fun refreshToken(refreshToken: String) = service.refreshToken(refreshToken)

    fun getUsers(accessTokenRealm: String) = service.getUsers(accessToken = accessTokenRealm)
}
