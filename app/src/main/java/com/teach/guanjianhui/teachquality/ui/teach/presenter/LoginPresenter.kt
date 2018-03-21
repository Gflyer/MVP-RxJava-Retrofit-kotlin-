package com.teach.guanjianhui.teachquality.ui.teach.presenter

import android.util.Log
import com.teach.guanjianhui.teachquality.base.BasePresenter
import com.teach.guanjianhui.teachquality.db.table.TeachTable
import com.teach.guanjianhui.teachquality.ui.teach.contract.LoginContact
import com.teach.guanjianhui.teachquality.ui.teach.model.DBModel

/**
 * 登录presenter
 * Created by guanjianhui on 18-3-20.
 */
class LoginPresenter : BasePresenter<LoginContact.View>(), LoginContact.Presenter {
    private val dbModel: DBModel by lazy { DBModel() }

    //学生验证
    override fun getStudentUserList() {
        Log.i("ssss123","123456")
        dbModel.getQueryData(TeachTable.StudentUser::class.java).subscribe({ list ->
            Log.i("ssss123","123456000")
            mRootView?.loadStudentSuccess(list)
        }, { error ->
            Log.i("ssss123","123456eee")
            mRootView?.loadFailure(error.message)
        })
    }

    //老师验证
    override fun getTeacherUserList() {
        dbModel.getQueryData(TeachTable.TeacherUser::class.java).subscribe({ list ->
            mRootView?.loadTeacherSuccess(list)
        }, { error ->
            mRootView?.loadFailure(error.message)
        })
    }

    //督导验证
    override fun getSuperVisorUserList() {
        dbModel.getQueryData(TeachTable.SupervisionUser::class.java).subscribe({ list ->
            mRootView?.loadSuperVisorSuccess(list)
        }, { error ->
            mRootView?.loadFailure(error.message)
        })
    }

    //管理员验证
    override fun getManagerUserList() {
        dbModel.getQueryData(TeachTable.ManagerUser::class.java).subscribe({ list ->
            mRootView?.loadManagerSuccess(list)
        }, { error ->
            mRootView?.loadFailure(error.message)
        })
    }

}