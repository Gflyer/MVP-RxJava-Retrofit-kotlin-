package com.teach.guanjianhui.teachquality.ui.teach.presenter

import com.teach.guanjianhui.teachquality.base.BasePresenter
import com.teach.guanjianhui.teachquality.beans.EvaTeachItem
import com.teach.guanjianhui.teachquality.db.table.SupervisionScore_Table
import com.teach.guanjianhui.teachquality.db.table.TeachTable
import com.teach.guanjianhui.teachquality.db.table.WorkmateScore_Table
import com.teach.guanjianhui.teachquality.ui.teach.contract.LeaderEvaContact
import com.teach.guanjianhui.teachquality.ui.teach.model.DBModel
import com.teach.guanjianhui.teachquality.ui.teach.model.EvaModel
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by guanjianhui on 18-3-28.
 */
class LeaderEvaPresenter : BasePresenter<LeaderEvaContact.View>(), LeaderEvaContact.Presenter {


    val dbModel by lazy { DBModel() }
    val evaModel by lazy { EvaModel() }

    lateinit var workmateList: List<TeachTable.WorkmateScore>
    lateinit var supervisorList: List<TeachTable.SupervisionScore>

    lateinit var teachTaskList: List<TeachTable.TeachingTask>
    var countWorkmate = 0
    var countSupervisor = 0


    //获取要评教的数据
    override fun getTeacherEvaData() {
        evaModel.getEvaDataList(TeachTable.TeachingTask::class.java, TeachTable.WorkmateScore::class.java, WorkmateScore_Table.teachingTaskNum, true)
                .subscribe({ list ->
                    if (++countWorkmate == 1) {
                        teachTaskList = list as List<TeachTable.TeachingTask>
                        //Log.i("guan6", teachTaskList.toString())
                    } else {//==2
                        workmateList = list as List<TeachTable.WorkmateScore>
                        mergeWorkmateList()
                    }
                })
    }

    //合并同行数据
    private fun mergeWorkmateList() {
        var teacherNameList: ArrayList<TeachTable.TeachingTask> = ArrayList()
        var k = 0

        if (workmateList.size == 0) {
            mRootView?.loadTeacherSuccess(teachTaskList as ArrayList<TeachTable.TeachingTask>)
            countWorkmate = 0
            return
        }


        for (i in 0..(teachTaskList.size - 1)) {//studentList
            for (j in k..(workmateList.size - 1)) {
                if (teachTaskList[i].teachingTaskNum == workmateList[j].teachingTaskNum) {
                    k++
                    break
                } else {
                    teacherNameList.add(teachTaskList[i])
                }
            }

            if (k == workmateList.size) {
                for (t in i + 1..(teachTaskList.size - 1)) {
                    teacherNameList.add(teachTaskList[t])
                }
                break
            }


        }
        mRootView?.loadTeacherSuccess(teacherNameList)
        countWorkmate = 0
    }

    //保存同行数据
    override fun saveTeacherScore(workmateNum: String, item: EvaTeachItem) {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var time = df.format(Date())
        var workmateEva = TeachTable.WorkmateScore(workmateNum, item.teachingTaskNum, item.attitudeScore, item.contentScore, item.methodScore, item.effectScore, item.orderScore, item.comment, time)
        dbModel.addData(workmateEva).subscribe({ isSuccess ->
            mRootView?.saveEvaSuccess()
        })
    }

    //获取督导数据
    override fun getSupervisorEvaData() {
        evaModel.getEvaDataList(TeachTable.TeachingTask::class.java, TeachTable.SupervisionScore::class.java, SupervisionScore_Table.teachingTaskNum, true)
                .subscribe({ list ->
                    if (++countWorkmate == 1) {
                        teachTaskList = list as List<TeachTable.TeachingTask>
                        //Log.i("guan6", teachTaskList.toString())
                    } else {//==2
                        supervisorList = list as List<TeachTable.SupervisionScore>
                        mergeSupervisorList()
                    }
                })
    }

    //保存督导数据
    override fun saveSupervisorScore(supervisorNum: String, item: EvaTeachItem) {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var time = df.format(Date())
        var supervisorEva = TeachTable.SupervisionScore(supervisorNum, item.teachingTaskNum, item.attitudeScore, item.contentScore, item.methodScore, item.effectScore, item.orderScore, item.comment, time)
        dbModel.addData(supervisorEva).subscribe({ isSuccess ->
            mRootView?.saveEvaSuccess()
        })
    }

    //合并督导数据
    private fun mergeSupervisorList() {
        var teacherNameList: ArrayList<TeachTable.TeachingTask> = ArrayList()
        var k = 0

        if (supervisorList.size == 0) {
            mRootView?.loadTeacherSuccess(teachTaskList as ArrayList<TeachTable.TeachingTask>)
            countSupervisor = 0
            return
        }


        for (i in 0..(teachTaskList.size - 1)) {//studentList
            for (j in k..(supervisorList.size - 1)) {
                if (teachTaskList[i].teachingTaskNum == supervisorList[j].teachingTaskNum) {
                    k++
                    break
                } else {
                    teacherNameList.add(teachTaskList[i])
                }
            }

            if (k == supervisorList.size) {
                for (t in i + 1..(teachTaskList.size - 1)) {
                    teacherNameList.add(teachTaskList[t])
                }
                break
            }


        }
        mRootView?.loadTeacherSuccess(teacherNameList)
        countSupervisor = 0
    }


}