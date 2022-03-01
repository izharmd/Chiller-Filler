package com.bws.chillerfiller.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bws.chillerfiller.itemcategory.productlist.PopulateProductDetailViewModel
import com.bws.chillerfiller.itemcategory.productlist.ProductListViewModel

class LoginFactory(private val repository: LoginRepository, val context: Context) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository, context) as T
        }

        if (modelClass.isAssignableFrom(ProductListViewModel::class.java)) {
            return ProductListViewModel(repository, context) as T
        }

        if (modelClass.isAssignableFrom(PopulateProductDetailViewModel::class.java)) {
            return PopulateProductDetailViewModel(repository, context) as T
        }



        throw IllegalArgumentException("Unknown class name")
    }
}