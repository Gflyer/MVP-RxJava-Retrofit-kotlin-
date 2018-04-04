package com.teach.guanjianhui.teachquality.beans

/**
 * 评价项
 * Created by guanjianhui on 18-3-28.
 */
data class EvaTeachItem(var teachingTaskNum: String, var courseNum: String,var courseName: String, var attitudeScore: Int = -1, var contentScore: Int = -1, var methodScore: Int = -1,
                        var effectScore: Int = -1, var orderScore: Int = -1, var comment: String)