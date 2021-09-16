package com.example.melnyk_sportea_app.retrofit

import com.example.melnyk_sportea_app.constants.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private var instance : Retrofit? = null
    fun getRetrofit(): Retrofit{
        if(instance==null){
            instance = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }
}