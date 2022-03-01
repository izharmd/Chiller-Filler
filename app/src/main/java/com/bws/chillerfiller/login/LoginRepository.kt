package com.bws.chillerfiller.login

import com.bws.chillerfiller.common.ApiInterface
import com.bws.chillerfiller.common.RetrofitHelper
import com.bws.chillerfiller.network.ProductListBody
import com.bws.chillerfiller.network.RequestBodies
import com.bws.chillerfiller.network.RetrofitInstance


class LoginRepository {
    val loginApi1 = RetrofitHelper.getInstance().create(ApiInterface::class.java)
    //  suspend fun loginUser(body: RequestBodies.LoginBody) = loginApi1.callLoginApi(body)

    suspend fun loginUser(body: RequestBodies.LoginBody) = loginApi1.callLoginApi(body)

    suspend fun getProductList(body: ProductListBody) = loginApi1.callProductListApi(body)

    suspend fun getPopulateProductDetails(body: RequestBodies.PopulateProductDetailsBody) = loginApi1.callPopulateProductDetails(body)


}