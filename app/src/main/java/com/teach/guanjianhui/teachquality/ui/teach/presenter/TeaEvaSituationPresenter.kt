package com.teach.guanjianhui.teachquality.ui.teach.presenter

import com.raizlabs.android.dbflow.sql.language.SQLite
import com.teach.guanjianhui.teachquality.base.BasePresenter
import com.teach.guanjianhui.teachquality.beans.TeaSituationBean
import com.teach.guanjianhui.teachquality.db.table.*
import com.teach.guanjianhui.teachquality.ui.teach.contract.TeaEvaSituationContact
import com.teach.guanjianhui.teachquality.ui.teach.model.DBModel
import java.math.BigDecimal
import java.util.*

/**
 * Created by guanjianhui on 18-4-10.
 */
class TeaEvaSituationPresenter : BasePresenter<TeaEvaSituationContact.View>(), TeaEvaSituationContact.Presenter {
    val dbModel: DBModel by lazy { DBModel() }
    var dataSize = 0
    val dataList by lazy { ArrayList<TeaSituationBean>() }

    override fun getData() {
        dbModel.getQueryData(TeachTable.TeachingTask::class.java).subscribe({ list ->
            mRootView?.loadSuccess(list)
            dataSize = list.size
        })
    }

    override fun getEvaData(teachTaskNum: String, id: Int) {
        var num = 0
        var studentScoreList: List<TeachTable.StudentScore>? = null
        var supervisorScoreList: List<TeachTable.SupervisionScore>? = null
        var workmateScoreList: List<TeachTable.WorkmateScore>? = null
        dbModel.getQueryData(TeachTable.StudentScore::class.java, StudentScore_Table.teachingTaskNum, teachTaskNum).subscribe({ list ->
            studentScoreList = list
            if (++num == 3) {
                getTeachingTask(id, teachTaskNum, studentScoreList!!, supervisorScoreList!!, workmateScoreList!!)
            }
        })
        dbModel.getQueryData(TeachTable.SupervisionScore::class.java, SupervisionScore_Table.teachingTaskNum, teachTaskNum).subscribe({ list ->
            supervisorScoreList = list
            if (++num == 3) {
                getTeachingTask(id, teachTaskNum, studentScoreList!!, supervisorScoreList!!, workmateScoreList!!)
            }
        })
        dbModel.getQueryData(TeachTable.WorkmateScore::class.java, WorkmateScore_Table.teachingTaskNum, teachTaskNum).subscribe({ list ->
            workmateScoreList = list
            if (++num == 3) {
                getTeachingTask(id, teachTaskNum, studentScoreList!!, supervisorScoreList!!, workmateScoreList!!)
            }
        })

    }


    private fun getTeachingTask(id: Int, teachTaskNum: String, studentScoreList: List<TeachTable.StudentScore>, supervisorScoreList: List<TeachTable.SupervisionScore>, workmateScoreList: List<TeachTable.WorkmateScore>) {
        var studentTotal = 0
        var supervisorTotal = 0
        var workmateTotal = 0

        if (studentScoreList.size > 0) {
            studentTotal = studentScoreList[0].attitudeScore + studentScoreList[0].contentScore + studentScoreList[0].methodScore + studentScoreList[0].effectScore + studentScoreList[0].orderScore
        }
        if (supervisorScoreList.size > 0) {
            supervisorTotal = supervisorScoreList[0].attitudeScore + supervisorScoreList[0].contentScore + supervisorScoreList[0].methodScore + supervisorScoreList[0].effectScore + supervisorScoreList[0].orderScore
        }
        if (workmateScoreList.size > 0) {
            workmateTotal = workmateScoreList[0].attitudeScore + workmateScoreList[0].contentScore + workmateScoreList[0].methodScore + workmateScoreList[0].effectScore + workmateScoreList[0].orderScore
        }

        val avg_score = studentTotal * getStudentPer() + supervisorTotal * getSuperVisorPer() + workmateTotal * getWorkmatePer()

        dbModel.getQueryData(TeachTable.TeachingTask::class.java, TeachingTask_Table.teachingTaskNum, teachTaskNum).subscribe({ list ->
            val data = TeaSituationBean(id, getInstituteName(list[0].teacherNum), getTeacherName(list[0].teacherNum), getCourseName(list[0].dyllabusNum), formateData(avg_score),
                    list[0].termNum, studentScoreList!!, supervisorScoreList!!, workmateScoreList!!, studentTotal, supervisorTotal, workmateTotal)
            dataList.add(data)
            if (dataList.size == dataSize) {
                Collections.sort(dataList)
                mRootView?.loadDataSuccess(dataList)
            }
        })
    }

    //四舍五入保留两位小数
    private fun formateData(num: Double): String {
        val decimal = BigDecimal(num.toString())
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString()

    }

    private fun getWorkmatePer(): Double {
        return SQLite.select(GradePercent_Table.workmatePer).from(TeachTable.GradePercent::class.java).querySingle()!!.workmatePer
    }

    private fun getStudentPer(): Double {
        return SQLite.select(GradePercent_Table.studentPer).from(TeachTable.GradePercent::class.java).querySingle()!!.studentPer
    }

    private fun getSuperVisorPer(): Double {
        return SQLite.select(GradePercent_Table.supervisionPer).from(TeachTable.GradePercent::class.java).querySingle()!!.supervisionPer
    }

    /**
     * 根据学期编号编号获取名称
     */
    private fun getTermName(termNum: String): String {
        val termNum = SQLite.select(TeachingTask_Table.dyllabusNum).from(TeachTable.TeachingTask::class.java).where(TeachingTask_Table.termNum.`is`(termNum)).querySingle()!!.termNum
        return getTerm(termNum)
    }

    private fun getTerm(courseNum: String): String {
        return SQLite.select().from(TeachTable.Syllabus::class.java).where(Syllabus_Table.courseNum.`is`(courseNum)).querySingle()!!.courseName
    }


    /**
     * 根据院系编号编号获取名称
     */
    private fun getInstituteName(teacherNum: String): String {
        val instituteNum = SQLite.select().from(TeachTable.TeacherInfo::class.java).where(TeacherInfo_Table.teacherNum.`is`(teacherNum)).querySingle()!!.instituteNum
        return SQLite.select().from(TeachTable.Institute::class.java).where(Institute_Table.instituteNum.`is`(instituteNum)).querySingle()!!.insituteName
    }


    private fun getTeacherName(teachNum: String): String {
        return SQLite.select(TeacherInfo_Table.name).from(TeachTable.TeacherInfo::class.java).where(TeacherInfo_Table.teacherNum.`is`(teachNum)).querySingle()!!.name
    }

    private fun getCourseName(courseNum: String): String {
        return SQLite.select(Syllabus_Table.courseName).from(TeachTable.Syllabus::class.java).where(Syllabus_Table.courseNum.`is`(courseNum)).querySingle()!!.courseName
    }
}