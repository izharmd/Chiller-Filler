package com.bws.chillerfiller.network
import com.bws.chillerfiller.common.ApiInterface
import com.bws.chillerfiller.util.Constants.BASE_URL_2
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        private val retrofitLogin by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl(BASE_URL_2)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val loginApi by lazy {
            retrofitLogin.create(ApiInterface::class.java)
        }

       /* val productListApi by lazy {
            retrofitLogin.create(ApiInterface::class.java)
        }*/
    }
}