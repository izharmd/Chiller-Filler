package com.bws.chillerfiller.login

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bws.chillerfiller.R
import com.bws.chillerfiller.network.ProductListBody
import com.bws.chillerfiller.network.RequestBodies
import com.bws.chillerfiller.util.NetworkUtils
import com.bws.chillerfiller.util.Resources2
import kotlinx.coroutines.launch
import retrofit2.Response


class LoginViewModel(val loginRepository: LoginRepository, val context: Context) : ViewModel() {

    //val loginResult = MutableLiveData<Response<ResponseLogin>>()
    var loginResult: MutableLiveData<Resources2<LoginResponse>>

    init {
        loginResult = MutableLiveData()
       // getUserLogin()
    }

    fun loginUser(body: RequestBodies.LoginBody) = viewModelScope.launch {
        login(body)
    }

    private suspend fun login(body: RequestBodies.LoginBody) {

        if(NetworkUtils.isNetworkAvailable(context)){
            loginResult.postValue(Resources2.Loading(loadingMessage = context.resources.getString(R.string.LOADING_PLEASE_WAIT)))

            try {
                val response = loginRepository.loginUser(body)
                loginResult.postValue(Resources2.Success(response.body()))

            }catch (e:Exception){
                loginResult.postValue(Resources2.Error(errorMessage = e.message.toString()))
            }
        }else{
            loginResult.postValue(Resources2.NoInternet(context.resources.getString(R.string.NO_INTERNET_CONNECTION)))
        }

    }

   /* private fun getUserLogin() {
        viewModelScope.launch {
            loginResult.postValue(Resources2.Loading(loadingMessage = context.resources.getString(R.string.LOADING_PLEASE_WAIT)))

            if (NetworkUtils.isNetworkAvailable(context)) {
                try {
                    val response = loginRepository.loginUser()

                        loginResult.postValue(Resources2.Success(response.body()))

                } catch (e: Exception) {
                    loginResult.postValue(Resources2.Error(errorMessage = e.message.toString()))
                }
            } else {
                loginResult.postValue(Resources2.NoInternet(context.resources.getString(R.string.NO_INTERNET_CONNECTION)))
            }
        }
    }*/

}