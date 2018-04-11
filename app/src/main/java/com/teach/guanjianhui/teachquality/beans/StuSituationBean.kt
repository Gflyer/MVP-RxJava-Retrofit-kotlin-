package com.teach.guanjianhui.teachquality.beans

/**
 * Created by guanjianhui on 18-4-9.
 */
data class StuSituationBean(val id: Int, val studentName: String, val teacherName: String, val courseName: String, val term: String = "2016-2017-1",
                            val attitudeScore: Int, val contentScore: Int, val methodScoe: Int, val effectScore: Int, val orderScore: Int,val totalScore:Int)
