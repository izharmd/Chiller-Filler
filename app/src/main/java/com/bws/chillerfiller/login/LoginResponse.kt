package com.bws.chillerfiller.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    /* @SerializedName("token")
     val token: String*/


    val AccessType: Any,
    val AccessUserName: Any,
    val ActivationUrl: Any,
    val Address1: Any,
    val Address2: Any,
    val Address3: Any,
    val BonusCardNumber: Any,
    val City: Any,
    val CompanyName: String,
    val Country: Any,
    val EmailID: String,
    val FirstName: String,
    val InvoiceFileName: Any,
    val InvoiceID: Any,
    val Invoicelist: Any,
    val IsActive: String,
    val IsBonusCard: Any,
    val LastName: String,
    val Message: String,
    val MobilePhone: Any,
    val OrderDate: Any,
    val OrderID_FK: Any,
    val OrderItemCount: Any,
    val OrderNumber: Any,
    val PaymentStatus: Any,
    val Postcode: String,
    val RestaurantContactID: Any,
    val RestaurantID_FK: Any,
    val State: Any,
    val Telephone: Any,
    val Title: Any,
    val TotalPayment: Any,
    val UserID: String,
    val UserName: Any,
    val WebPortalID: String,
    val password: Any
)