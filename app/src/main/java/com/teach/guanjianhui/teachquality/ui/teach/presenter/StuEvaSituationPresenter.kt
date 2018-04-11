package com.teach.guanjianhui.teachquality.ui.teach.presenter

import android.util.Log
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.teach.guanjianhui.teachquality.base.BasePresenter
import com.teach.guanjianhui.teachquality.beans.StuSituationBean
import com.teach.guanjianhui.teachquality.db.table.*
import com.teach.guanjianhui.teachquality.ui.teach.contract.StuEvaSituationContact
import com.teach.guanjianhui.teachquality.ui.teach.model.DBModel

/**
 * Created by guanjianhui on 18-4-9.
 */
class StuEvaSituationPresenter : BasePresenter<StuEvaSituationContact.View>(), StuEvaSituationContact.Presenter {

    val dbModel: DBModel by lazy { DBModel() }

    override fun getData() {
        dbModel.getQueryData(TeachTable.StudentScore::class.java).subscribe({ list ->
            val stuScoreList = ArrayList<StuSituationBean>()
            for ((index, item) in list.withIndex()) {
                var totalScore = item.attitudeScore + item.contentScore + item.methodScore + item.effectScore + item.orderScore
                stuScoreList.add(StuSituationBean(index + 1, getStudentName(item.studentNum!!), getTeacherName(item.teachingTaskNum!!), getCourseName(item.teachingTaskNum!!), getTermName(item.teachingTaskNum!!)
                        , item.attitudeScore, item.contentScore, item.methodScore, item.effectScore, item.orderScore, totalScore))
            }
            mRootView?.loadSuccess(stuScoreList)
        })
    }


    /**
     * 根据学期编号编号获取名称
     */
    private fun getTermName(teachingTaskNum: String): String {
        return SQLite.select(TeachingTask_Table.termNum).from(TeachTable.TeachingTask::class.java).where(TeachingTask_Table.teachingTaskNum.`is`(teachingTaskNum)).querySingle()!!.termNum

    }

//    private fun getTerm(courseNum: String): String {
//        return SQLite.select().from(TeachTable.Syllabus::class.java).where(Syllabus_Table.courseNum.`is`(courseNum)).querySingle()!!.courseName
//    }

    /**
     * 根据课程编号编号获取名称
     */
    private fun getCourseName(teachingTaskNum: String): String {
        val courseNum = SQLite.select(TeachingTask_Table.dyllabusNum).from(TeachTable.TeachingTask::class.java).where(TeachingTask_Table.teachingTaskNum.`is`(teachingTaskNum)).querySingle()!!.dyllabusNum
        return getCourse(courseNum)
    }

    private fun getCourse(courseNum: String): String {
        return SQLite.select(Syllabus_Table.courseName).from(TeachTable.Syllabus::class.java).where(Syllabus_Table.courseNum.`is`(courseNum)).querySingle()!!.courseName
    }

    /**
     * 根据教师编号获取名字
     */
    private fun getTeacherName(teachingTaskNum: String): String {
        val teacherNum = SQLite.select(TeachingTask_Table.teacherNum).from(TeachTable.TeachingTask::class.java).where(TeachingTask_Table.teachingTaskNum.`is`(teachingTaskNum)).querySingle()!!.teacherNum
        return getTeacher(teacherNum)
    }


    private fun getTeacher(teachNum: String): String {
        return SQLite.select(TeacherInfo_Table.name).from(TeachTable.TeacherInfo::class.java).where(TeacherInfo_Table.teacherNum.`is`(teachNum)).querySingle()!!.name
    }

    /**
     * 根据学生编号获取名字
     */
    private fun getStudentName(studentNum: String): String {
        return SQLite.select(StudentInfo_Table.name).from(TeachTable.StudentInfo::class.java).where(StudentInfo_Table.studentNum.`is`(studentNum)).querySingle()!!.name
    }

}