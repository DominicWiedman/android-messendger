package com.example.androidmessendger.base

import java.lang.reflect.ParameterizedType

abstract class ABaseRestApi<S> : IRestApi {
    private val client: IRestClient
    val service: S

    constructor(client: IRestClient) {
        val type = javaClass.genericSuperclass as ParameterizedType
        val sClass = type.actualTypeArguments[0] as Class<S>

        this.client = client
        service = client.createService(sClass)
    }

    override fun cancelAllRequests() {
        client.cancelAllRequests()
    }
}
