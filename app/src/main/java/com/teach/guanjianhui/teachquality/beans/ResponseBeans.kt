package com.teach.guanjianhui.teachquality.beans

/**
 * data数据类
 * Created by guanjianhui on 18-2-1.
 */
data class ResponseBeans(val unique:Int){
    data class TestResponseBean(val id:Int,val title:String,val url:String)
    data class ListResponseBean(val status:Int,val data:ArrayList<ListItemBean>,val msg:String)
}