package com.teach.guanjianhui.teachquality.constant

/**
 * Created by guanjianhui on 18-3-20.
 */
object Field{
    const val LOGIN_STUDENT=0X01
    const val LOGIN_TEACHER=0X02
    const val LOGIN_SUPERVISOR=0X03
    const val LOGIN_Manager=0X04

    /******************sp状态********************/
    const val LOGIN_STATUS="login_status"

    const val REM_STUDENT_USERNAME="rem_student_username"
    const val REM_STUDENT_PASSWORD="rem_student_password"

    const val REM_TEACHER_USERNAME="rem_teacher_username"
    const val REM_TEACHER_PASSWORD="rem_teacher_password"

    const val REM_SUPERVISOR_USERNAME="rem_supervisor_username"
    const val REM_SUPERVISOR_PASSWORD="rem_supervisor_password"

    const val REM_MANAGER_USERNAME="rem_manager_username"
    const val REM_MANAGER_PASSWORD="rem_manager_password"

    const val IS_REM="is_rem"


    /******************指标类型********************/
    const val EVALUATE_ATTITUDE=0X01
    const val EVALUATE_CONTENT=0X02
    const val EVALUATE_METHOD=0X03
    const val EVALUATE_EFFECT=0X04
    const val EVALUATE_ORDER=0X05


    /******************评分类型********************/
    const val EVALUATE_EXCELLENT=0X11
    const val EVALUATE_GOOD=0X12
    const val EVALUATE_QUALITY=0X13
    const val EVALUATE_UNQUALITY=0X14






}