package com.example.androidmessendger.domain.repositories.rest.api

import android.util.Log
import com.example.androidmessendger.base.ABaseRestApi
import com.example.androidmessendger.base.IRestClient
import com.example.androidmessendger.domain.di.modules.NetModule
import com.example.androidmessendger.domain.repositories.models.rest.Message
import com.example.androidmessendger.domain.repositories.models.rest.User
import com.example.androidmessendger.domain.repositories.rest.service.IMessengerRestApiService
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class DialogRestApi : ABaseRestApi<IMessengerRestApiService> {

    @Inject
    constructor(client: IRestClient) : super(client)


//    fun uploadAvatar(file: File): Observable<UploadedFile> {
//
//        val part = MultipartBody.Part.createFormData("file",
//            file.name + ".jpg",
//            MultipartBody.create(MediaType.parse("image/*"), file)
//        )
//
//        return service.uploadAvatar(part)
//    }


    fun getMessages(
        accessTokenRealm: String,
        from: Int
    ) = service.getMessages(
        accessToken = accessTokenRealm,
        from = from
    )

    fun sendMessage(accessTokenRealm: String, message: String, to: Int) =
        service.sendMessage(
            accessToken = accessTokenRealm,
            message = Message(message = message, to = to)
        )

    fun getNewMessages(accessTokenRealm: String) =
        service.getNewMessages(accessToken = accessTokenRealm)

}
