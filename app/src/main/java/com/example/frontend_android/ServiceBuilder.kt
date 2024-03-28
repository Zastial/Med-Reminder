package com.example.frontend_android

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HeaderInterceptor : Interceptor {

    private val credentials = Credentials.basic(GlobalProperties.apiUsername, GlobalProperties.apiPassword)

    override fun intercept(chain: Interceptor.Chain): Response = chain.run {
        proceed(
            request()
                .newBuilder()
                .addHeader("Authorization", credentials)
                .build()
        )
    }
}

object ServiceBuilder {
    //CREATE HTTP CLIENT
    private val headerInterceptor = HeaderInterceptor()

    //CREATE HTTP CLIENT
    private val okHttp = OkHttpClient.Builder().apply {
        //auth
        this.addInterceptor(headerInterceptor)
    }


    //retrofit builder
    private val builder = Retrofit.Builder().baseUrl(GlobalProperties.apiUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())


    //create retrofit Instance
    private val retrofit = builder.build()

    //we will use this class to create an anonymous inner class function that
    //implements Country service Interface
    fun <T> buildService (serviceType: Class<T>): T {
        return retrofit.create(serviceType)
    }

}

