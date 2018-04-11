package com.teach.guanjianhui.teachquality.ui.teach.activity

import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseActivity
import com.teach.guanjianhui.teachquality.beans.StuSituationBean
import com.teach.guanjianhui.teachquality.ui.teach.adapter.StuSituationAdapter
import com.teach.guanjianhui.teachquality.ui.teach.contract.StuEvaSituationContact
import com.teach.guanjianhui.teachquality.ui.teach.presenter.StuEvaSituationPresenter
import kotlinx.android.synthetic.main.activity_stu_eva_situation.*
import kotlinx.android.synthetic.main.tool_title.*

/**
 * Created by guanjianhui on 18-4-4.
 */
class StuEvaSituationActivity : BaseActivity(), StuEvaSituationContact.View {
    var myList: List<StuSituationBean>? = null
    val mPresenter: StuEvaSituationPresenter by lazy { StuEvaSituationPresenter() }
    override fun layoutId(): Int {
        return R.layout.activity_stu_eva_situation
    }

    override fun initView() {
        mPresenter.attachView(this)
        tv_title.text = "学生评分详情"
        tv_end.visibility= View.GONE
    }

    override fun initData() {
        mPresenter.getData()
    }

    override fun initListener() {
        tool_title.setNavigationOnClickListener { finish() }
    }

    override fun detachView() {
        mPresenter.detachView()
    }

    override fun loadSuccess(list: List<StuSituationBean>) {
        myList = list
        val myAdapter = StuSituationAdapter(this, list)
        //item点击事件监听
        myAdapter.setOnItemClickListener(object : StuSituationAdapter.OnItemClickListener {
            override fun onClick(position: Int) {
                showEvaDialog(position)
            }
        })
        val linearLayoutManager = LinearLayoutManager(this)
        rec_stu_manager.layoutManager = linearLayoutManager
        rec_stu_manager.adapter = myAdapter
    }

    private fun showEvaDialog(position: Int) {
        val myDialog: AlertDialog = AlertDialog.Builder(this, R.style.StudentEvaDialog).create()
        val view = LayoutInflater.from(this).inflate(R.layout.dialog_stu_manager_situation, null)
        val tv_stu_manager_term = view.findViewById<TextView>(R.id.tv_stu_manager_term)
        val tv_stu_attitude_score = view.findViewById<TextView>(R.id.tv_stu_attitude_score)
        val tv_stu_content_score = view.findViewById<TextView>(R.id.tv_stu_content_score)
        val tv_stu_method_score = view.findViewById<TextView>(R.id.tv_stu_method_score)
        val tv_stu_effect_score = view.findViewById<TextView>(R.id.tv_stu_effect_score)
        val tv_stu_order_score = view.findViewById<TextView>(R.id.tv_stu_order_score)
        val layout_stu_manager_yes = view.findViewById<RelativeLayout>(R.id.layout_stu_manager_yes)

        tv_stu_manager_term.text = "---" + myList!![position]?.term
        tv_stu_attitude_score.text = myList!![position]?.attitudeScore.toString()
        tv_stu_content_score.text = myList!![position]?.contentScore.toString()
        tv_stu_method_score.text = myList!![position]?.methodScoe.toString()
        tv_stu_effect_score.text = myList!![position]?.effectScore.toString()
        tv_stu_order_score.text = myList!![position]?.orderScore.toString()

        layout_stu_manager_yes.setOnClickListener {
            if (myDialog.isShowing) myDialog.dismiss()
        }

        myDialog.setCanceledOnTouchOutside(false)
        myDialog.setView(view)
        myDialog.show()

    }


    override fun loadFailure() {
    }

}