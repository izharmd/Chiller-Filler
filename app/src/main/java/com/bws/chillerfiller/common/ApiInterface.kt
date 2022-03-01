package com.bws.chillerfiller.common

import com.bws.chillerfiller.itemcategory.productlist.PopulateProductDetailsResponse
import com.bws.chillerfiller.itemcategory.productlist.ProductMenuResponse
import com.bws.chillerfiller.login.LoginResponse
import com.bws.chillerfiller.network.ProductListBody
import com.bws.chillerfiller.network.RequestBodies
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("UserloginDetails")
    suspend fun callLoginApi(@Body loginPram: RequestBodies.LoginBody): Response<LoginResponse>

    @POST("Populateproductmenu")
    suspend fun callProductListApi(@Body loginPram: ProductListBody): Response<ProductMenuResponse>

    @POST("PopulateProductDetais")
    suspend fun callPopulateProductDetails(@Body populateProDetailPram: RequestBodies.PopulateProductDetailsBody): Response<PopulateProductDetailsResponse>
}