package com.example.androidmessendger.domain.repositories.rest.api

import android.util.Log
import com.example.androidmessendger.base.ABaseRestApi
import com.example.androidmessendger.base.IRestClient
import com.example.androidmessendger.domain.di.modules.NetModule
import com.example.androidmessendger.domain.repositories.models.rest.User
import com.example.androidmessendger.domain.repositories.rest.service.IUserRestApiService
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class DialogRestApi : ABaseRestApi<IUserRestApiService> {

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


    fun getUsers(accessTokenRealm: String) = service.getUsers(accessToken = accessTokenRealm)

}
