package com.glinyanov.childstars.api

import retrofit2.http.Body
import retrofit2.http.POST
import com.glinyanov.childstars.api.request.ExposedUser
import com.glinyanov.childstars.api.response.GetChildResponse
import com.glinyanov.childstars.api.response.OtpResponse
import com.glinyanov.childstars.api.response.RegistrationResponse
import com.glinyanov.childstars.api.response.ResponseWrapper


interface ChildStarsService {

    @POST("/addUser")
    suspend fun registration(@Body params: ExposedUser): ResponseWrapper<RegistrationResponse>

    @POST("/login")
    suspend fun login(@Body params: ExposedUser): ResponseWrapper<RegistrationResponse>

    @POST("/getOtp")
    suspend fun getOtp(@Body params: ExposedUser): ResponseWrapper<OtpResponse>

    @POST("/getChilds")
    suspend fun getChilds(@Body params: ExposedUser): ResponseWrapper<GetChildResponse>

}