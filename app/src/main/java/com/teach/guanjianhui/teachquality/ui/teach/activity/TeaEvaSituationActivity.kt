package com.teach.guanjianhui.teachquality.ui.teach.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseActivity
import com.teach.guanjianhui.teachquality.beans.TeaSituationBean
import com.teach.guanjianhui.teachquality.beans.TeaSituationDetailBean
import com.teach.guanjianhui.teachquality.db.table.GradePercent_Table
import com.teach.guanjianhui.teachquality.db.table.TeachTable
import com.teach.guanjianhui.teachquality.ui.teach.adapter.TeaSituationAdapter
import com.teach.guanjianhui.teachquality.ui.teach.contract.TeaEvaSituationContact
import com.teach.guanjianhui.teachquality.ui.teach.presenter.TeaEvaSituationPresenter
import com.teach.guanjianhui.teachquality.widgets.ViewPagerDialog
import kotlinx.android.synthetic.main.activity_tea_eva_situation.*
import kotlinx.android.synthetic.main.tool_title.*

/**
 * Created by guanjianhui on 18-4-4.
 */
class TeaEvaSituationActivity : BaseActivity(), TeaEvaSituationContact.View {
    var myList: List<TeaSituationBean>? = null
    var supervisorPer: String? = null
    var studentPer: String? = null
    var workmatePer: String? = null


    val mPresenter by lazy { TeaEvaSituationPresenter() }
    override fun layoutId(): Int {
        return R.layout.activity_tea_eva_situation
    }

    override fun initListener() {
        tool_title.setNavigationOnClickListener { finish() }
    }

    override fun initData() {
        mPresenter.getData()
        supervisorPer = "(" + getSuperVisorPer() * 100 + "%)"
        studentPer = "(" + getStudentPer() * 100 + "%)"
        workmatePer = "(" + getWorkmatePer() * 100 + "%)"
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

    override fun initView() {
        tv_title.text = "教师评分详情"
        mPresenter.attachView(this)
        tv_end.visibility=View.GONE
    }

    override fun detachView() {
        mPresenter.detachView()
    }

    override fun loadSuccess(list: List<TeachTable.TeachingTask>) {
        for ((index, item) in list.withIndex()) {
            mPresenter.getEvaData(item.teachingTaskNum, index + 1)
        }
    }

    override fun loadDataSuccess(list: List<TeaSituationBean>) {
        myList = list

        val myAdapter = TeaSituationAdapter(this, list)
        myAdapter.setOnItemClickListener(object : TeaSituationAdapter.OnItemClickListener {
            override fun onClick(position: Int) {
                showTeaDialog(position)
            }

        })
        val linearLayoutManager = LinearLayoutManager(this)
        rec_tea_manager.adapter = myAdapter
        rec_tea_manager.layoutManager = linearLayoutManager
    }

    private fun showTeaDialog(position: Int) {
        val detailList = ArrayList<TeaSituationDetailBean>()
        //supervisor
        if (myList!![position]!!.supervisorScoreList.size > 0) {
            detailList.add(TeaSituationDetailBean("校督导评价", supervisorPer!!, myList!![position].supervisorScoreList[0].attitudeScore, myList!![position].supervisorScoreList[0].contentScore, myList!![position].supervisorScoreList[0].methodScore, myList!![position].supervisorScoreList[0].effectScore, myList!![position].supervisorScoreList[0].orderScore, myList!![position].supervisorTotal))
        } else {
            detailList.add(TeaSituationDetailBean("校督导评价", supervisorPer!!, 0, 0, 0, 0, 0, myList!![position].supervisorTotal))
        }
        //student
        if (myList!![position]!!.studentScoreList.size > 0) {
            detailList.add(TeaSituationDetailBean("学生评价", studentPer!!, myList!![position].studentScoreList[0].attitudeScore, myList!![position].studentScoreList[0].contentScore, myList!![position].studentScoreList[0].methodScore, myList!![position].studentScoreList[0].effectScore, myList!![position].studentScoreList[0].orderScore, myList!![position].studentTotal))
        } else {
            detailList.add(TeaSituationDetailBean("学生评价", studentPer!!, 0, 0, 0, 0, 0, myList!![position].studentTotal))
        }

        if (myList!![position]!!.workmateScoreList.size > 0) {
            detailList.add(TeaSituationDetailBean("院系部评价", workmatePer!!, myList!![position].workmateScoreList[0].attitudeScore, myList!![position].workmateScoreList[0].contentScore, myList!![position].workmateScoreList[0].methodScore, myList!![position].workmateScoreList[0].effectScore, myList!![position].workmateScoreList[0].orderScore, myList!![position].workmateTotal))
        } else {
            detailList.add(TeaSituationDetailBean("院系部评价", workmatePer!!, 0, 0, 0, 0, 0, myList!![position].workmateTotal))
        }

        val myDialog = ViewPagerDialog(this, detailList)
        myDialog.show()
    }

    override fun loadFailure() {

    }
}
