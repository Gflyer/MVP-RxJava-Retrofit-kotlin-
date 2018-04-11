package com.teach.guanjianhui.teachquality.ui.teach.activity


import android.app.DatePickerDialog
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseActivity
import com.teach.guanjianhui.teachquality.constant.Field
import com.teach.guanjianhui.teachquality.db.table.TeachTable
import com.teach.guanjianhui.teachquality.ui.teach.contract.ManagerSystemContact
import com.teach.guanjianhui.teachquality.ui.teach.presenter.ManagerSystemPresenter
import com.teach.guanjianhui.teachquality.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_manager_system.*
import kotlinx.android.synthetic.main.tool_title.*
import java.sql.Date

/**
 * Created by guanjianhui on 18-4-4.
 */
class ManagerSystemActivity : BaseActivity(), ManagerSystemContact.View {

    val mPresenter: ManagerSystemPresenter by lazy { ManagerSystemPresenter() }


    override fun layoutId(): Int {
        return R.layout.activity_manager_system
    }

    override fun initView() {
        mPresenter.attachView(this)
        tv_title.text="评分系统管理"
        tv_end.text="确定"
    }

    override fun initData() {
        mPresenter.getData()
    }

    override fun initListener() {
        //修改日期
        tv_supervisor_choose.setOnClickListener {
            val date = tv_supervisor_date.text.toString().trim()
            val dateList = date.split("-")
            showDatePicker(Field.LOGIN_SUPERVISOR, dateList[0].toInt(), dateList[1].toInt(), dateList[2].toInt())
        }
        tv_teacher_choose.setOnClickListener {
            val date = tv_teacher_date.text.toString().trim()
            val dateList = date.split("-")
            showDatePicker(Field.LOGIN_TEACHER, dateList[0].toInt(), dateList[1].toInt(), dateList[2].toInt())
        }
        tv_student_choose.setOnClickListener {
            val date = tv_student_date.text.toString().trim()
            val dateList = date.split("-")
            showDatePicker(Field.LOGIN_STUDENT, dateList[0].toInt(), dateList[1].toInt(), dateList[2].toInt())
        }


        //提交修改
        tv_supervisor_commit.setOnClickListener {
            mPresenter.updateData(TeachTable.DeadLine(Field.LOGIN_SUPERVISOR, Date.valueOf(tv_supervisor_date.text.toString().trim())))
        }
        tv_teacher_commit.setOnClickListener {
            mPresenter.updateData(TeachTable.DeadLine(Field.LOGIN_TEACHER, Date.valueOf(tv_teacher_date.text.toString().trim())))
        }
        tv_student_commit.setOnClickListener {
            mPresenter.updateData(TeachTable.DeadLine(Field.LOGIN_STUDENT, Date.valueOf(tv_student_date.text.toString().trim())))
        }

        tv_end.setOnClickListener { finish() }
        tool_title.setNavigationOnClickListener { finish() }

    }

    private fun showDatePicker(type: Int, mYear: Int, mMonth: Int, mDay: Int) {
        val dateDialog = DatePickerDialog(this, R.style.MyDatePickerDialogTheme, { view, year, month, dayOfMonth ->
            var mMonth = month + 1
            when (type) {
                Field.LOGIN_SUPERVISOR -> tv_supervisor_date.text = "$year-$mMonth-$dayOfMonth"
                Field.LOGIN_TEACHER -> tv_teacher_date.text = "$year-$mMonth-$dayOfMonth"
                Field.LOGIN_STUDENT -> tv_student_date.text = "$year-$mMonth-$dayOfMonth"
            }
        }, mYear, mMonth - 1, mDay)

        val datePicker = dateDialog.datePicker
        datePicker.minDate = System.currentTimeMillis()
        dateDialog.show()

    }

    override fun loadSuccess(list: List<TeachTable.DeadLine>) {
        for (item in list) {
            when (item.userType) {
                Field.LOGIN_SUPERVISOR -> tv_supervisor_date.text = item.deadLine.toString()
                Field.LOGIN_TEACHER -> tv_teacher_date.text = item.deadLine.toString()
                Field.LOGIN_STUDENT -> tv_student_date.text = item.deadLine.toString()
            }
        }
    }

    override fun loadFailure(msg: String) {
        ToastUtils.showToast(this, msg)
    }

    override fun updateCompete(msg: String) {
        ToastUtils.showToast(this, msg)
    }

    override fun detachView() {
        mPresenter.detachView()
    }

}