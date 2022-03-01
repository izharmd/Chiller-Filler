package com.bws.chillerfiller.repository

import com.bws.chillerfiller.network.RequestBodies
import com.bws.chillerfiller.network.RetrofitInstance

class AppRepository {

   // suspend fun getPictures() = RetrofitInstance.picsumApi.getPictures()

   // suspend fun loginUser(body: RequestBodies.LoginBody) = RetrofitInstance.loginApi.loginUser(body)

    suspend fun testFun() = "Test is pass"
}