package com.bws.chillerfiller.itemcategory.productlist

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bws.chillerfiller.R
import com.bws.chillerfiller.login.LoginRepository
import com.bws.chillerfiller.network.ProductListBody
import com.bws.chillerfiller.network.RequestBodies
import com.bws.chillerfiller.util.NetworkUtils
import com.bws.chillerfiller.util.Resources2
import kotlinx.coroutines.launch

class PopulateProductDetailViewModel(val repository: LoginRepository,val context: Context):ViewModel(){

    val resultPopulateProductDetails:MutableLiveData<Resources2<PopulateProductDetailsResponse>>

    init {
        resultPopulateProductDetails = MutableLiveData()
    }

    fun populateProductDetails(body: RequestBodies.PopulateProductDetailsBody) = viewModelScope.launch {
        productDetails(body)
    }


    private suspend fun productDetails(body: RequestBodies.PopulateProductDetailsBody){

        if(NetworkUtils.isNetworkAvailable(context)){
            resultPopulateProductDetails.postValue(Resources2.Loading(loadingMessage = context.resources.getString(R.string.LOADING_PLEASE_WAIT)))

            try {
                val response = repository.getPopulateProductDetails(body)
                resultPopulateProductDetails.postValue(Resources2.Success(response.body()))

            }catch (e:Exception){
                resultPopulateProductDetails.postValue(Resources2.Error(errorMessage = e.message.toString()))
            }
        }else{
            resultPopulateProductDetails.postValue(Resources2.NoInternet(context.resources.getString(R.string.NO_INTERNET_CONNECTION)))
        }
    }
}