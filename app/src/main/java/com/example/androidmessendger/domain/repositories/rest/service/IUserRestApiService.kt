package com.example.androidmessendger.domain.repositories.rest.service

import com.example.androidmessendger.domain.repositories.models.rest.Token
import com.example.androidmessendger.domain.repositories.models.rest.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface IUserRestApiService {
    @PUT("/user/v1/registration")
    fun registration(@Body user: User): Observable<User>

    @POST("/user/v1/login")
    fun login(@Body user: User): Observable<User>

    @POST("/user/v1/refresh")
    @Headers("Content-Type: application/json")
    fun refreshToken(
        @Header("refresh_token") refreshToken: String
    ): Call<Token>

    @GET("/user/v1/users")
    fun getUsers(@Header("access_token") accessToken: String): Observable<List<User>>
}