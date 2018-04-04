package com.teach.guanjianhui.teachquality.beans

/**
 * 评价指标item
 * Created by guanjianhui on 18-3-21.
 */
data class EvaluateItemBean(var type:Int,var title:String,var itemList:List<EvaluateRowBean>):Comparable<EvaluateItemBean>{
    //排序
    override fun compareTo(other: EvaluateItemBean): Int {
        if (type>other.type){
            return 1
        }else{
            return -1
        }
    }
}