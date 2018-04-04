package com.teach.guanjianhui.teachquality.ui.teach.activity

import android.content.Intent
import android.support.v4.view.ViewPager
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseActivity
import com.teach.guanjianhui.teachquality.beans.EvaluateItemBean
import com.teach.guanjianhui.teachquality.db.table.TeachTable
import com.teach.guanjianhui.teachquality.ui.teach.adapter.EvaluateAdapter
import com.teach.guanjianhui.teachquality.ui.teach.contract.EvaluateContract
import com.teach.guanjianhui.teachquality.ui.teach.presenter.EvaluatePresenter
import kotlinx.android.synthetic.main.activity_evaluate.*
import kotlinx.android.synthetic.main.tool_title.*

/**
 * 评估activity
 * Created by guanjianhui on 18-3-21.
 */
class EvaluateActivity : BaseActivity(), EvaluateContract.View {
    override fun saveEvaSuccess() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadTeacherSuccess(list: List<TeachTable.TeachingTask>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    val mPresemter by lazy { EvaluatePresenter() }
    lateinit var list: List<EvaluateItemBean>
    var studentNum: String? = null

    override fun initView() {
        mPresemter.attachView(this)
        tool_title.setNavigationIcon(null)
        tv_title.setText("评教规则")
        tv_end.setText("下一步")
    }

    override fun initData() {
        mPresemter.getData()
        studentNum = intent.getStringExtra("studentNum")
    }

    override fun initListener() {
        vp_evaluate.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                tv_evaluate.setText(list[position].title)
                when (position) {
                    0 -> im_evaluate.setImageResource(R.mipmap.ic_eva_attitude)
                    1 -> im_evaluate.setImageResource(R.mipmap.ic_eva_content)
                    2 -> im_evaluate.setImageResource(R.mipmap.ic_eva_method)
                    3 -> im_evaluate.setImageResource(R.mipmap.ic_eva_effect)
                    4 -> im_evaluate.setImageResource(R.mipmap.ic_eva_order)


                }
            }
        })

        tv_end.setOnClickListener {
            //跳转
            val studentIntent = Intent(this, StudentEvaActivity::class.java)
            studentIntent.putExtra("studentNum", studentNum)
            startActivity(studentIntent)
        }

        tool_title.setNavigationOnClickListener {
            finish()
        }

    }

    override fun layoutId(): Int {
        return R.layout.activity_evaluate
    }

    override fun detachView() {
        mPresemter.detachView()
    }

    override fun loadSuccess(list: List<EvaluateItemBean>) {
        this.list = list
        vp_evaluate.offscreenPageLimit = 3
        vp_evaluate.adapter = EvaluateAdapter(list, this)
    }

    override fun loadFailure(msg: String) {

    }

}