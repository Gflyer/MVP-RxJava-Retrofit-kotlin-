package com.teach.guanjianhui.teachquality.beans

import android.util.Log
import com.teach.guanjianhui.teachquality.db.table.TeachTable

/**
 * Created by guanjianhui on 18-4-10.
 */
data class TeaSituationBean(val id: Int, val institute: String, val teacherName: String, val courseName: String, val avg_score: String, val term: String,
                            val studentScoreList: List<TeachTable.StudentScore>, val supervisorScoreList: List<TeachTable.SupervisionScore>,
                            val workmateScoreList: List<TeachTable.WorkmateScore>, val studentTotal: Int, val supervisorTotal: Int, val workmateTotal: Int) : Comparable<TeaSituationBean> {
    //排序
    override fun compareTo(other: TeaSituationBean): Int {
        if (id > other.id) {
            return 1
        } else {
            return -1
        }
    }
}