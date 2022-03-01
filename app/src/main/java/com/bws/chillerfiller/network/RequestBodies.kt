package com.bws.chillerfiller.network

object RequestBodies {
    data class LoginBody(
        val EmailID: String,
        val password: String
    )

    data class PopulateProductDetailsBody(
        val RestaurantID: String,
        val Categories: String,
        val ProuctType: String,
        val ProductVersion: String,
        val PageSize: String,
        val CurrentPageNo: String,
        val SortBy: String,
        val SortDirection: String
    )
}

class ProductListBody {

}