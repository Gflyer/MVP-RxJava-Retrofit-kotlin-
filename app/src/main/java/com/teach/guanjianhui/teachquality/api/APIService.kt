package com.teach.guanjianhui.teachquality.api

import com.teach.guanjianhui.teachquality.beans.ResponseBeans
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * 接口请求方法
 * Created by guanjianhui on 18-1-30.
 */
interface APIService {
    @POST("v4/discovery")
    fun getTestList(): Observable<ResponseBeans.TestResponseBean>//< >泛型里面为观察者observer的类型
    //测试获取list
    @GET("teacher?")
    fun getListData(@Query("type") type:String,@Query("num") num:Int):Observable<ResponseBeans.ListResponseBean>

}
