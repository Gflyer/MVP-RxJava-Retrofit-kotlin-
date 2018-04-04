package com.teach.guanjianhui.teachquality.ui.teach.presenter

import android.util.Log
import com.teach.guanjianhui.teachquality.base.BasePresenter
import com.teach.guanjianhui.teachquality.beans.EvaluateItemBean
import com.teach.guanjianhui.teachquality.beans.EvaluateRowBean
import com.teach.guanjianhui.teachquality.beans.EvaluateStuBean
import com.teach.guanjianhui.teachquality.constant.Field
import com.teach.guanjianhui.teachquality.db.table.EvaluateScore_Table
import com.teach.guanjianhui.teachquality.db.table.TeachTable
import com.teach.guanjianhui.teachquality.db.table.TeachingTask_Table
import com.teach.guanjianhui.teachquality.ui.teach.contract.EvaluateContract
import com.teach.guanjianhui.teachquality.ui.teach.model.DBModel
import com.teach.guanjianhui.teachquality.ui.teach.model.EvaModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by guanjianhui on 18-3-21.
 */
class EvaluatePresenter : BasePresenter<EvaluateContract.View>(), EvaluateContract.Presenter {


    private var count = 0
    private var countTeacher = 0
    val dbModel by lazy { DBModel() }
    val evaModel by lazy { EvaModel() }
    var myList = ArrayList<EvaluateItemBean>()
    lateinit var scoreList: List<TeachTable.EvaluateScore>

    lateinit var studentList: List<TeachTable.StudentScore>
    lateinit var teachTaskList: List<TeachTable.TeachingTask>

    private lateinit var attitudeList: ArrayList<TeachTable.EvaluateScore>
    private lateinit var contentList: ArrayList<TeachTable.EvaluateScore>
    private lateinit var methodList: ArrayList<TeachTable.EvaluateScore>
    private lateinit var effectList: ArrayList<TeachTable.EvaluateScore>
    private lateinit var orderList: ArrayList<TeachTable.EvaluateScore>

    //先按关系查询
    override fun getData() {
        //态度
        dbModel.getQueryData(TeachTable.EvaluateScore::class.java, EvaluateScore_Table.evaluateType, Field.EVALUATE_ATTITUDE).subscribe({ list ->
            attitudeList = list as ArrayList<TeachTable.EvaluateScore>
            if (++count == 5) {
                getListData()
            }
        })
        //内容
        dbModel.getQueryData(TeachTable.EvaluateScore::class.java, EvaluateScore_Table.evaluateType, Field.EVALUATE_CONTENT).subscribe({ list ->
            contentList = list as ArrayList<TeachTable.EvaluateScore>
            if (++count == 5) {
                getListData()
            }
        })
        //方法
        dbModel.getQueryData(TeachTable.EvaluateScore::class.java, EvaluateScore_Table.evaluateType, Field.EVALUATE_METHOD).subscribe({ list ->
            methodList = list as ArrayList<TeachTable.EvaluateScore>
            if (++count == 5) {
                getListData()
            }
        })
        //效果
        dbModel.getQueryData(TeachTable.EvaluateScore::class.java, EvaluateScore_Table.evaluateType, Field.EVALUATE_EFFECT).subscribe({ list ->
            effectList = list as ArrayList<TeachTable.EvaluateScore>
            if (++count == 5) {
                getListData()
            }
        })
        //秩序
        dbModel.getQueryData(TeachTable.EvaluateScore::class.java, EvaluateScore_Table.evaluateType, Field.EVALUATE_ORDER).subscribe({ list ->
            orderList = list as ArrayList<TeachTable.EvaluateScore>
            if (++count == 5) {
                getListData()
            }
        })


    }

    private fun getListData() {
        dbModel.getQueryData(TeachTable.TeachAttitude::class.java).subscribe({ list ->
            var attitudeItemList = ArrayList<EvaluateRowBean>()
            attitudeItemList.apply {
                for (item in list) {
                    add(EvaluateRowBean(item._id, item.evaluateIndex, attitudeList[0].excellentTotal, attitudeList[0].goodTotal, attitudeList[0].qualifiedTotal, attitudeList[0].unqualifiedTotal))
                }
                //
                myList.add(EvaluateItemBean(Field.EVALUATE_ATTITUDE, "教学态度(" + attitudeList[0].totalScore + ")", attitudeItemList))
                Collections.sort(myList)
                if (++count == 10) {
                    mRootView?.loadSuccess(myList)
                    count = 0
                }
            }
        })


        dbModel.getQueryData(TeachTable.TeachContent::class.java).subscribe({ list ->
            var contentItemList = ArrayList<EvaluateRowBean>()
            contentItemList.apply {
                for (item in list) {
                    add(EvaluateRowBean(item._id, item.evaluateIndex, contentList[0].excellentTotal, contentList[0].goodTotal, contentList[0].qualifiedTotal, contentList[0].unqualifiedTotal))
                }
                myList.add(EvaluateItemBean(Field.EVALUATE_CONTENT, "教学内容(" + contentList[0].totalScore + ")", contentItemList))
                Collections.sort(myList)
                if (++count == 10) {
                    mRootView?.loadSuccess(myList)
                    count = 0
                }
            }

        })
        dbModel.getQueryData(TeachTable.TeachMethod::class.java).subscribe({ list ->
            var methodItemList = ArrayList<EvaluateRowBean>()
            methodItemList.apply {
                for (item in list) {
                    add(EvaluateRowBean(item._id, item.evaluateIndex, methodList[0].excellentTotal, methodList[0].goodTotal, methodList[0].qualifiedTotal, methodList[0].unqualifiedTotal))
                }
                myList.add(EvaluateItemBean(Field.EVALUATE_METHOD, "教学方法(" + methodList[0].totalScore + ")", methodItemList))
                Collections.sort(myList)
                if (++count == 10) {
                    mRootView?.loadSuccess(myList)
                    count = 0
                }
            }

        })
        dbModel.getQueryData(TeachTable.TeachEffect::class.java).subscribe({ list ->
            var effectItemList = ArrayList<EvaluateRowBean>()
            effectItemList.apply {
                for (item in list) {
                    add(EvaluateRowBean(item._id, item.evaluateIndex, effectList[0].excellentTotal, effectList[0].goodTotal, effectList[0].qualifiedTotal, effectList[0].unqualifiedTotal))
                }
                myList.add(EvaluateItemBean(Field.EVALUATE_EFFECT, "教学效果(" + effectList[0].totalScore + ")", effectItemList))
                Collections.sort(myList)
                if (++count == 10) {
                    mRootView?.loadSuccess(myList)
                    count = 0
                }
            }

        })
        dbModel.getQueryData(TeachTable.TeachOrder::class.java).subscribe({ list ->
            var orderItemList = ArrayList<EvaluateRowBean>()
            orderItemList.apply {
                for (item in list) {
                    add(EvaluateRowBean(item._id, item.evaluateIndex, orderList[0].excellentTotal, orderList[0].goodTotal, orderList[0].qualifiedTotal, orderList[0].unqualifiedTotal))
                }
                myList.add(EvaluateItemBean(Field.EVALUATE_ORDER, "教学秩序(" + orderList[0].totalScore + ")", orderItemList))
                Collections.sort(myList)
                if (++count == 10) {
                    mRootView?.loadSuccess(myList)
                    count = 0
                }
            }

        })
    }

    //查询未评价教师编号
    override fun getTeacherList() {


        dbModel.getQueryData(TeachTable.TeachingTask::class.java, TeachingTask_Table.teachingTaskNum, true).subscribe({ list ->
            teachTaskList = list
            if (++countTeacher == 2) {
                mergeList()
            }
        })
        dbModel.getQueryData(TeachTable.StudentScore::class.java, TeachingTask_Table.teachingTaskNum, true).subscribe({ list ->
            studentList = list
            if (++countTeacher == 2) {
                mergeList()
            }
        })
    }

    private fun mergeList() {
        var teacherNameList: ArrayList<TeachTable.TeachingTask> = ArrayList()
        var k = 0

        if (studentList.size == 0) {
            mRootView?.loadTeacherSuccess(teachTaskList as ArrayList<TeachTable.TeachingTask>)
            countTeacher = 0
            return
        }

        for (i in 0..(teachTaskList.size - 1)) {//studentList
            for (j in k..(studentList.size - 1)) {
                if (teachTaskList[i].teachingTaskNum == studentList[j].teachingTaskNum) {
                    k++
                    break
                } else {
                    teacherNameList.add(teachTaskList[i])
                }
            }

            if (k == studentList.size) {
                for (t in i + 1..(teachTaskList.size - 1)) {
                    teacherNameList.add(teachTaskList[t])
                }
                break
            }


        }
        mRootView?.loadTeacherSuccess(teacherNameList)
        countTeacher = 0
    }


    override fun saveTeacherEva(list: List<EvaluateStuBean>, teachingTaskNum: String?, studentNum: String?) {
        var attitudeScore = 0
        var contentScore = 0
        var methodScore = 0
        var effectScore = 0
        var orderScore = 0

        for (item in list) {
            when (item.type) {
                Field.EVALUATE_ATTITUDE -> attitudeScore += getMyScore(item.scoreType, scoreList[0])
                Field.EVALUATE_CONTENT -> contentScore += getMyScore(item.scoreType, scoreList[1])
                Field.EVALUATE_METHOD -> methodScore += getMyScore(item.scoreType, scoreList[2])
                Field.EVALUATE_EFFECT -> effectScore += getMyScore(item.scoreType, scoreList[3])
                Field.EVALUATE_ORDER -> orderScore += getMyScore(item.scoreType, scoreList[4])
            }
        }

        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var time = df.format(Date())

        val studentEvaScore = TeachTable.StudentScore(studentNum, teachingTaskNum, attitudeScore, contentScore, methodScore, effectScore, orderScore, "", time)


        dbModel.addData(studentEvaScore).subscribe({ isSuccess ->
            if (isSuccess) {
                mRootView?.saveEvaSuccess()
            } else {
                mRootView?.loadFailure("保存学生评分失败")
            }

        })


    }

    private fun getMyScore(scoreType: Int, item: TeachTable.EvaluateScore): Int {
        when (scoreType) {
            Field.EVALUATE_EXCELLENT -> return item.excellentTotal
            Field.EVALUATE_GOOD -> return item.goodTotal
            Field.EVALUATE_QUALITY -> return item.qualifiedTotal
            Field.EVALUATE_UNQUALITY -> return item.unqualifiedTotal
        }
        return 0
    }

    fun getScore() {

        dbModel.getQueryData(TeachTable.EvaluateScore::class.java).subscribe({ list ->
            scoreList = list
        })

    }

}