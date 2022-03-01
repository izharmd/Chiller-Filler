package com.bws.chillerfiller.itemcategory.productlist

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bws.chillerfiller.R
import com.bws.chillerfiller.login.LoginRepository
import com.bws.chillerfiller.network.ProductListBody
import com.bws.chillerfiller.util.NetworkUtils
import com.bws.chillerfiller.util.Resources2
import kotlinx.coroutines.launch

class ProductListViewModel(val repository: LoginRepository,val context: Context):ViewModel() {
    
    
    var result:MutableLiveData<Resources2<ProductMenuResponse>>
    
    init {
        result = MutableLiveData()
    }
    
    fun httpProductMenu(body: ProductListBody) = viewModelScope.launch { 
        productList(body)
    }

  private suspend fun productList(body: ProductListBody){

      if(NetworkUtils.isNetworkAvailable(context)){
          result.postValue(Resources2.Loading(loadingMessage = context.resources.getString(R.string.LOADING_PLEASE_WAIT)))

          try {
              val response = repository.getProductList(body)
              result.postValue(Resources2.Success(response.body()))

          }catch (e:Exception){
              result.postValue(Resources2.Error(errorMessage = e.message.toString()))
          }
      }else{
          result.postValue(Resources2.NoInternet(context.resources.getString(R.string.NO_INTERNET_CONNECTION)))
      }
   }
}