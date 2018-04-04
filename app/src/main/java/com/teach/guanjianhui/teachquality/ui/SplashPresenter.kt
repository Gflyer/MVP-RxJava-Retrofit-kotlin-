package com.teach.guanjianhui.teachquality.ui

import com.teach.guanjianhui.teachquality.base.BasePresenter
import com.teach.guanjianhui.teachquality.db.table.TeachTable
import com.teach.guanjianhui.teachquality.ui.teach.model.DBModel
import java.sql.Date

/**
 * Created by guanjianhui on 18-3-22.
 */
class SplashPresenter : BasePresenter<SplashContact.View>(), SplashContact.Presenter {
    private val dbModel: DBModel by lazy { DBModel() }
    private var count = 0
    override fun saveEvaluateData() {
        val attitudeList = ArrayList<TeachTable.TeachAttitude>()
        val contentList = ArrayList<TeachTable.TeachContent>()
        val methodList = ArrayList<TeachTable.TeachMethod>()
        val effectList = ArrayList<TeachTable.TeachEffect>()
        val orderList = ArrayList<TeachTable.TeachOrder>()
        val evaluateScoreList = ArrayList<TeachTable.EvaluateScore>()//分数表

        //测试数据，教师评分表
        val teachingTaskList = ArrayList<TeachTable.TeachingTask>()
        val studentScoreList = ArrayList<TeachTable.StudentScore>()

        //教师信息表
        val teacherInfoList = ArrayList<TeachTable.TeacherInfo>()

        //督导信息表
        val supervisorInfoList = ArrayList<TeachTable.SupervisionInfo>()

        val courseList = ArrayList<TeachTable.Syllabus>()

        val studentUserList = ArrayList<TeachTable.StudentUser>()
        val teacherUserList = ArrayList<TeachTable.TeacherUser>()
        val supervisorUserList = ArrayList<TeachTable.SupervisionUser>()
        val managerUserList = ArrayList<TeachTable.ManagerUser>()


        studentUserList.apply {
            add(TeachTable.StudentUser("123456", "000000"))
            add(TeachTable.StudentUser("123457", "000000"))
            add(TeachTable.StudentUser("123458", "000000"))
        }

        teacherUserList.apply {
            add(TeachTable.TeacherUser("223456", "000000"))
            add(TeachTable.TeacherUser("223457", "000000"))
            add(TeachTable.TeacherUser("223458", "000000"))

        }

        supervisorUserList.apply {
            add(TeachTable.SupervisionUser("323456", "000000"))
            add(TeachTable.SupervisionUser("323457", "000000"))
            add(TeachTable.SupervisionUser("323458", "000000"))
        }

        managerUserList.apply {
            add(TeachTable.ManagerUser("423456", "000000"))
            add(TeachTable.ManagerUser("423457", "000000"))
            add(TeachTable.ManagerUser("423458", "000000"))
        }


        attitudeList.apply {
            add(TeachTable.TeachAttitude(1, "提前到达岗位，认真做好准备工作，按时上下课"))
            add(TeachTable.TeachAttitude(2, "合理管理课堂秩序，并按规定落实学生考勤制度"))
            add(TeachTable.TeachAttitude(3, "教学认真，授课内容准备充分，组织有序"))
            add(TeachTable.TeachAttitude(4, "布置作业合理，认真批改作业"))
        }

        contentList.apply {
            add(TeachTable.TeachContent(5, "合理选用优秀新版教材"))
            add(TeachTable.TeachContent(6, "授课内容符合大纲要求，基本知识讲解清楚，重点难点突出"))
            add(TeachTable.TeachContent(7, "授课内容符合大纲要求，基本知识讲解清楚，重点难点突出"))
            add(TeachTable.TeachContent(8, "授课内容符合大纲要求，基本知识讲解清楚，重点难点突出"))

        }

        methodList.apply {
            add(TeachTable.TeachMethod(9, "授课语言清晰，表达清楚，能够吸引学生注意力"))
            add(TeachTable.TeachMethod(10, "教学方法灵活多样，注重启迪学生思维，重点突出"))
            add(TeachTable.TeachMethod(11, "课件规范，板书安排合理，并充分利用多媒体和其他教学手段"))
            add(TeachTable.TeachMethod(12, "注重培养学生分析问题和解决问题的综合能力"))
            add(TeachTable.TeachMethod(13, "能调动学生学习气氛，教师与学生课堂互动和谐"))

        }

        effectList.apply {
            add(TeachTable.TeachEffect(14, "较好地掌握了本门课程的主要内容知识"))
            add(TeachTable.TeachEffect(15, "教学综合效果反映较好，提高了对本课程的学习兴趣"))
            add(TeachTable.TeachEffect(16, "通过本课程的学习，提高了相关职业技能和职业素质"))

        }

        orderList.apply {
            add(TeachTable.TeachOrder(17, "出勤率高，迟到率低"))
            add(TeachTable.TeachOrder(18, "课堂秩序良好，学生认真听讲"))
        }

        evaluateScoreList.apply {
            add(TeachTable.EvaluateScore(0x01, 5, 4, 3, 2, 20))
            add(TeachTable.EvaluateScore(0x02, 5, 4, 3, 2, 20))
            add(TeachTable.EvaluateScore(0x03, 6, 5, 4, 3, 30))
            add(TeachTable.EvaluateScore(0x04, 6, 5, 4, 3, 20))
            add(TeachTable.EvaluateScore(0x05, 5, 4, 3, 2, 10))

        }

        teachingTaskList.apply {
            add(TeachTable.TeachingTask("1114001", "200001", "400001", "101", "309"))
            add(TeachTable.TeachingTask("1114002", "200002", "400002", "101", "309"))
            add(TeachTable.TeachingTask("1114003", "200003", "400003", "101", "309"))
            add(TeachTable.TeachingTask("1114004", "200004", "400005", "101", "309"))
            add(TeachTable.TeachingTask("1114005", "200005", "400004", "101", "309"))
            add(TeachTable.TeachingTask("1114006", "200005", "400005", "101", "309"))

        }

        studentScoreList.apply {
            add(TeachTable.StudentScore("311400", "1114001", 20, 18, 20, 20, 10, null, "2018-03-26"))
            add(TeachTable.StudentScore("311401", "1114002", 20, 18, 20, 20, 10, null, "2018-03-26"))
            add(TeachTable.StudentScore("311402", "1114003", 20, 18, 20, 20, 10, null, "2018-03-26"))

        }

        teacherInfoList.apply {
            add(TeachTable.TeacherInfo("200001", "小红帽", "女", Date.valueOf("1970-03-28"), "研究生", "硕士", "大神", true, "null", "外太空", "500001"))
            add(TeachTable.TeacherInfo("200002", "大灰狼", "男", Date.valueOf("1980-04-21"), "研究生", "硕士", "坑比", true, "null", "银河系", "500002"))
            add(TeachTable.TeacherInfo("200003", "吴彦祖", "女", Date.valueOf("1985-03-22"), "研究生", "硕士", "脑残", false, "null", "广东", "500001"))
            add(TeachTable.TeacherInfo("200004", "彭于晏", "女", Date.valueOf("1952-03-20"), "博士生", "博士", "天秀", false, "null", "新疆", "500002"))
            add(TeachTable.TeacherInfo("200005", "吴晓波", "女", Date.valueOf("2018-03-28"), "本科", "学士", "陈独秀", true, "null", "黑龙江", "500003"))

        }

        supervisorInfoList.apply {
            add(TeachTable.SupervisionInfo("30001", "佟丽娅", "女", "500002"))
            add(TeachTable.SupervisionInfo("30002", "罗大佑", "男", "500001"))
            add(TeachTable.SupervisionInfo("30003", "鞠婧祎", "女", "500003"))
        }

        courseList.apply {
            add(TeachTable.Syllabus("400001", "安卓开发", 1))
            add(TeachTable.Syllabus("400002", "java开发", 2))
            add(TeachTable.Syllabus("400003", "kotlin开发", 2))
            add(TeachTable.Syllabus("400004", "数据库开发", 1))
            add(TeachTable.Syllabus("400005", "迷之开发", 3))

        }



        dbModel.addDataList(attitudeList).subscribe(
                { isSuccess ->
                    if (isSuccess) {
                        mRootView?.saveSuccess("态度添加成功")
                        if (++count == 6) {
                            mRootView?.startActivity()
                        }
                    } else {
                        mRootView?.saveFailure()
                    }
                }, { error ->
        })

        dbModel.addDataList(contentList).subscribe(
                { isSuccess ->
                    if (isSuccess) {
                        mRootView?.saveSuccess("内容添加成功")
                        if (++count == 6) {
                            mRootView?.startActivity()
                        }
                    } else {
                        mRootView?.saveFailure()
                    }
                }, { error ->
        })

        dbModel.addDataList(methodList).subscribe(
                { isSuccess ->
                    if (isSuccess) {
                        mRootView?.saveSuccess("方法添加成功")
                        if (++count == 6) {
                            mRootView?.startActivity()
                        }
                    } else {
                        mRootView?.saveFailure()
                    }
                }, { error ->
        })

        dbModel.addDataList(effectList).subscribe(
                { isSuccess ->
                    if (isSuccess) {
                        mRootView?.saveSuccess("效果添加成功")
                        if (++count == 6) {
                            mRootView?.startActivity()
                        }
                    } else {
                        mRootView?.saveFailure()
                    }
                }, { error ->
        })

        dbModel.addDataList(orderList).subscribe(
                { isSuccess ->
                    if (isSuccess) {
                        mRootView?.saveSuccess("秩序添加成功")
                        if (++count == 6) {
                            mRootView?.startActivity()
                        }
                    } else {
                        mRootView?.saveFailure()
                    }
                }, { error ->
        })

        dbModel.addDataList(evaluateScoreList).subscribe(
                { isSuccess ->
                    if (isSuccess) {
                        mRootView?.saveSuccess("总分添加成功")
                        if (++count == 6) {
                            mRootView?.startActivity()
                        }
                    } else {
                        mRootView?.saveFailure()
                    }
                }, { error ->
        })

        dbModel.addDataList(teachingTaskList).subscribe(
                { isSuccess ->
                    if (isSuccess) {
                        mRootView?.saveSuccess("授课任务添加成功")
                        if (++count == 6) {
                            mRootView?.startActivity()
                        }
                    } else {
                        mRootView?.saveFailure()
                    }
                }, { error ->
        })

        dbModel.addDataList(studentScoreList).subscribe(
                { isSuccess ->
                    if (isSuccess) {
                        mRootView?.saveSuccess("学生评分添加成功")
                        if (++count == 6) {
                            mRootView?.startActivity()
                        }
                    } else {
                        mRootView?.saveFailure()
                    }
                }, { error ->
        })

        dbModel.addDataList(teacherInfoList).subscribe(
                { isSuccess ->
                    if (isSuccess) {
                        mRootView?.saveSuccess("教师信息添加成功")
                        if (++count == 6) {
                            mRootView?.startActivity()
                        }
                    } else {
                        mRootView?.saveFailure()
                    }
                }, { error ->
        })

        dbModel.addDataList(courseList).subscribe(
                { isSuccess ->
                    if (isSuccess) {
                        mRootView?.saveSuccess("课程信息添加成功")
                        if (++count == 6) {
                            mRootView?.startActivity()
                        }
                    } else {
                        mRootView?.saveFailure()
                    }
                }, { error ->
        })

        dbModel.addDataList(studentUserList).subscribe(
                { isSuccess ->
                    if (isSuccess) {
                        mRootView?.saveSuccess("学生账户添加成功")
                        if (++count == 6) {
                            mRootView?.startActivity()
                        }
                    } else {
                        mRootView?.saveFailure()
                    }
                }, { error ->
        })

        dbModel.addDataList(teacherUserList).subscribe(
                { isSuccess ->
                    if (isSuccess) {
                        mRootView?.saveSuccess("教师账户添加成功")
                        if (++count == 6) {
                            mRootView?.startActivity()
                        }
                    } else {
                        mRootView?.saveFailure()
                    }
                }, { error ->
        })

        dbModel.addDataList(supervisorUserList).subscribe(
                { isSuccess ->
                    if (isSuccess) {
                        mRootView?.saveSuccess("督导账户添加成功")
                        if (++count == 6) {
                            mRootView?.startActivity()
                        }
                    } else {
                        mRootView?.saveFailure()
                    }
                }, { error ->
        })

        dbModel.addDataList(managerUserList).subscribe(
                { isSuccess ->
                    if (isSuccess) {
                        mRootView?.saveSuccess("管理员账户添加成功")
                        if (++count == 6) {
                            mRootView?.startActivity()
                        }
                    } else {
                        mRootView?.saveFailure()
                    }
                }, { error ->
        })
    }

}