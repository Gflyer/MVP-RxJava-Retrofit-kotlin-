package com.teach.guanjianhui.teachquality.net

import com.teach.guanjianhui.teachquality.api.APIService
import com.teach.guanjianhui.teachquality.api.UriConstant
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * rxJava网络请求封装
 * Created by guanjianhui on 18-1-30.
 */

//object修饰的类是一个单例
object RetrofitManager{

    private var mClient:OkHttpClient?=null
    private var mRetrofit:Retrofit?=null

    val service:APIService by lazy {
        if (mRetrofit==null){
            synchronized(RetrofitManager::class.java){
                if (mRetrofit==null){
                    initRetrofit()
                }
            }
        }
        mRetrofit!!.create(APIService::class.java)
    }

    private fun initClient(){
     mClient=OkHttpClient.Builder()
             .connectTimeout(30L,TimeUnit.SECONDS)
             .readTimeout(10L,TimeUnit.SECONDS)
             .writeTimeout(15L,TimeUnit.SECONDS)
             .build()

    }

    private fun initRetrofit(){
        initClient()
        mRetrofit=Retrofit.Builder()
                .baseUrl(UriConstant.BASE_URL)
                .client(mClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())//使用gson解析数据
                .build()

    }


}