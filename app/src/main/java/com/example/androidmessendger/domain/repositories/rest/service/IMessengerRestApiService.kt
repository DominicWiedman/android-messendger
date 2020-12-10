package com.example.androidmessendger.domain.repositories.rest.service

import com.example.androidmessendger.domain.repositories.models.rest.Message
import retrofit2.http.*
import java.util.*
import io.reactivex.Observable

interface IMessengerRestApiService {

    @GET("/messenger/v1/messages")
    fun getMessages(
        @Header("access_token") accessToken: String,
        @Query("from") from: Int
    ): Observable<List<Message>>

    @GET("/messenger/v1/new_messages")
    fun getNewMessages(@Header("access_token") accessToken: String): Observable<List<Message>>

    @POST("/messenger/v1/send")
    fun sendMessage(
        @Header("access_token") accessToken: String,
        @Body message: Message
    ): Observable<Message>

    @GET("/messenger/v1/online")
    fun getOnline(@Header("access_token") accessToken: String)

}