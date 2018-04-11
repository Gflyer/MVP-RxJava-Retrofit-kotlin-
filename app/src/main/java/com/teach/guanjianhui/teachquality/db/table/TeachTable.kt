package com.teach.guanjianhui.teachquality.db.table

import android.util.Log
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.NotNull
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
//import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import com.raizlabs.android.dbflow.structure.BaseModel
import com.teach.guanjianhui.teachquality.db.TeachDatabase
import java.sql.Date


/**
 * Created by guanjianhui on 18-3-12.
 */
class TeachTable {


    //1.教师信息表
    @Table(database = TeachDatabase::class)
    data class TeacherInfo(@PrimaryKey var teacherNum: String = "000",
                           @Column @NotNull var name: String = "undef",
                           @Column @NotNull var sex: String = "undef",
                           @Column @NotNull var birthDate: Date = Date.valueOf("2018-03-13"),
                           @Column var education: String? = null,
                           @Column var degree: String? = null,
                           @Column var professionalTitle: String? = null,
                           @Column @NotNull var isDoubleTeacher: Boolean = false,
                           @Column @NotNull var certificationUrl: String = "null",
                           @Column var teacherSource: String? = null,
                           @Column @NotNull var instituteNum: String = "000"
    ) : BaseRXModel()

    //2.学生信息表
    @Table(database = TeachDatabase::class)
    data class StudentInfo(@PrimaryKey var studentNum: String = "000",
                           @Column @NotNull var name: String = "undef",
                           @Column @NotNull var sex: String = "undef",
                           @Column @NotNull var classNum: String = "000"
    ) : BaseRXModel()

    //3.督导信息表
    @Table(database = TeachDatabase::class)
    data class SupervisionInfo(@PrimaryKey var supervisionNum: String = "000",
                               @Column @NotNull var name: String = "undef",
                               @Column @NotNull var sex: String = "undef",
                               @Column @NotNull var instituteNum: String = "000"
    ) : BaseRXModel()

    //4.课程表
    @Table(database = TeachDatabase::class)
    data class Syllabus(@PrimaryKey var courseNum: String = "000",
                        @Column @NotNull var courseName: String = "undef",
                        @Column @NotNull var courseType: Int = 0x00
    ) : BaseRXModel()

    //5.院系表
    @Table(database = TeachDatabase::class)
    data class Institute(@PrimaryKey var instituteNum: String = "000",
                         @Column @NotNull var insituteName: String = "undef"
    ) : BaseRXModel()

    //6.班级表
    @Table(database = TeachDatabase::class)
    data class Class(@PrimaryKey var classNum: String = "000",
                     @Column @NotNull var className: String = "undef",
                     @Column @NotNull var instituteNum: String = "000"
    ) : BaseRXModel()

    //7.授课任务表
    @Table(database = TeachDatabase::class)
    data class TeachingTask(@PrimaryKey var teachingTaskNum: String = "000",
                            @Column @NotNull var teacherNum: String = "000",
                            @Column @NotNull var dyllabusNum: String = "000",
                            @Column @NotNull var termNum: String = "000",
                            @Column @NotNull var classNum: String = "000"
    ) : BaseRXModel()

    //8.三方评分权重比例表
    @Table(database = TeachDatabase::class)
    data class GradePercent(
            @PrimaryKey(autoincrement = true) var _id: Int = 0,
            @Column @NotNull var supervisionPer: Double = 0.00,
            @Column @NotNull var studentPer: Double = 0.00,
            @Column @NotNull var workmatePer: Double = 0.00
    ) : BaseRXModel()

    //9.截止日期表
    @Table(database = TeachDatabase::class)
    data class DeadLine(@PrimaryKey var userType: Int = 0x00,
                        @Column @NotNull var deadLine: Date = Date.valueOf("2018-03-13")
    ) : BaseRXModel()

    //10.学期表
    @Table(database = TeachDatabase::class)
    data class Term(@PrimaryKey var termNum: String = "000", var termName: String = "2017-2018-2"
    ) : BaseRXModel()

    //11.学生用户表
    @Table(database = TeachDatabase::class)
    data class StudentUser(@PrimaryKey var studentNum: String = "000",
                           @Column @NotNull var password: String = "***"
    ) : BaseRXModel()

    //12.教师用户表
    @Table(database = TeachDatabase::class)
    data class TeacherUser(@PrimaryKey var teacherNum: String = "000",
                           @Column @NotNull var password: String = "***"
    ) : BaseRXModel()

    //13.督导用户表
    @Table(database = TeachDatabase::class)
    data class SupervisionUser(@PrimaryKey var supervisionNum: String = "000",
                               @Column @NotNull var password: String = "***"
    ) : BaseRXModel()

    //14.管理员用户表
    @Table(database = TeachDatabase::class)
    data class ManagerUser(@PrimaryKey var managerNum: String = "000",
                           @Column @NotNull var nickName: String = "undef",
                           @Column @NotNull var password: String = "***",
                           @Column @NotNull var instituteNum: String = "000"
    ) : BaseRXModel()

    //15.学生评分表
    @Table(database = TeachDatabase::class)
    data class StudentScore(@PrimaryKey var studentNum: String? = "000",
                            @PrimaryKey var teachingTaskNum: String? = "000",
                            @Column @NotNull var attitudeScore: Int = 0,
                            @Column @NotNull var contentScore: Int = 0,
                            @Column @NotNull var methodScore: Int = 0,
                            @Column @NotNull var effectScore: Int = 0,
                            @Column @NotNull var orderScore: Int = 0,
                            @Column var comment: String? = null,
                            @Column @NotNull var commentTime: String = "undef"
    ) : BaseRXModel()

    //16.同行评分表
    @Table(database = TeachDatabase::class)
    data class WorkmateScore(@PrimaryKey var workmateNum: String = "000",
                             @PrimaryKey var teachingTaskNum: String = "000",
                             @Column @NotNull var attitudeScore: Int = 0,
                             @Column @NotNull var contentScore: Int = 0,
                             @Column @NotNull var methodScore: Int = 0,
                             @Column @NotNull var effectScore: Int = 0,
                             @Column @NotNull var orderScore: Int = 0,
                             @Column var comment: String? = null,
                             @Column @NotNull var commentTime: String = "undef"
    ) : BaseRXModel()

    //17.督导评分表
    @Table(database = TeachDatabase::class)
    data class SupervisionScore(@PrimaryKey var supervisionNum: String = "000",
                                @PrimaryKey var teachingTaskNum: String = "000",
                                @Column @NotNull var attitudeScore: Int = 0,
                                @Column @NotNull var contentScore: Int = 0,
                                @Column @NotNull var methodScore: Int = 0,
                                @Column @NotNull var effectScore: Int = 0,
                                @Column @NotNull var orderScore: Int = 0,
                                @Column var comment: String? = null,
                                @Column @NotNull var commentTime: String = "undef"
    ) : BaseRXModel()

    //18.教学态度评分表
    @Table(database = TeachDatabase::class)
    data class TeachAttitude(@PrimaryKey(autoincrement = true) var _id: Int = 0,
                             @Column @NotNull var evaluateIndex: String = "undef"
    ) : BaseRXModel()

    //19.教学内容评分表
    @Table(database = TeachDatabase::class)
    data class TeachContent(@PrimaryKey(autoincrement = true) var _id: Int = 0,
                            @Column @NotNull var evaluateIndex: String = "undef"
    ) : BaseRXModel()

    //20.教学方法评分表
    @Table(database = TeachDatabase::class)
    data class TeachMethod(@PrimaryKey(autoincrement = true) var _id: Int = 0,
                           @Column @NotNull var evaluateIndex: String = "undef"
    ) : BaseRXModel()

    //21.教学效果评分表
    @Table(database = TeachDatabase::class)
    data class TeachEffect(@PrimaryKey(autoincrement = true) var _id: Int = 0,
                           @Column @NotNull var evaluateIndex: String = "undef"
    ) : BaseRXModel()

    //22.教学秩序评分表
    @Table(database = TeachDatabase::class)
    data class TeachOrder(@PrimaryKey(autoincrement = true) var _id: Int = 0,
                          @Column @NotNull var evaluateIndex: String = "undef"
    ) : BaseRXModel()

    //23.评价分数表
    @Table(database = TeachDatabase::class)
    data class EvaluateScore(@PrimaryKey var evaluateType: Int = 0x00,
                             @Column @NotNull var excellentTotal: Int = 0,
                             @Column @NotNull var goodTotal: Int = 0,
                             @Column @NotNull var qualifiedTotal: Int = 0,
                             @Column @NotNull var unqualifiedTotal: Int = 0,
                             @Column @NotNull var totalScore: Int = 0
    ) : BaseRXModel()
}