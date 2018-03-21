package com.teach.guanjianhui.teachquality.ui.teach.contract

import com.teach.guanjianhui.teachquality.base.IBaseView
import com.teach.guanjianhui.teachquality.db.table.TeachTable

/**
 * Created by guanjianhui on 18-3-20.
 */
class LoginContact {
    interface View : IBaseView {
        /**
         * 获取学生用户list成功回调
         */
        fun loadStudentSuccess(list: List<TeachTable.StudentUser>)

        /**
         * 获取教师用户list成功回调
         */
        fun loadTeacherSuccess(list: List<TeachTable.TeacherUser>)

        /**
         * 获取督导用户list成功回调
         */
        fun loadSuperVisorSuccess(list: List<TeachTable.SupervisionUser>)

        /**
         * 获取管理员用户list成功回调
         */
        fun loadManagerSuccess(list: List<TeachTable.ManagerUser>)


        fun loadFailure(msg:String?)
    }

    interface Presenter {

        /**
         * 获取学生用户表list
         */
        fun getStudentUserList()

        /**
         * 获取教师用户list
         */
        fun getTeacherUserList()

        /**
         * 获取督导用户list
         */
        fun getSuperVisorUserList()

        /**
         * 获取管理员用户list
         */
        fun getManagerUserList()
    }
}