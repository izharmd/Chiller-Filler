package com.bws.chillerfiller.network
import com.bws.chillerfiller.login.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface API {

   /* @GET("v2/list")
    suspend fun getPictures(): Response<PicsResponse>*/

   // @POST("UserloginDetails")
   @POST("api/login")
    suspend fun loginUser(@Body body:RequestBodies.LoginBody): Response<LoginResponse>


}