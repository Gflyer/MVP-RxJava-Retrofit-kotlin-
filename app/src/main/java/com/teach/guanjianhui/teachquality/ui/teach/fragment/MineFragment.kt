package com.teach.guanjianhui.teachquality.ui.teach.fragment


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseFragment
import com.teach.guanjianhui.teachquality.beans.ListItemBean
import com.teach.guanjianhui.teachquality.ui.teach.adapter.ListTestAdapter
import com.teach.guanjianhui.teachquality.ui.teach.contract.MineContract
import com.teach.guanjianhui.teachquality.ui.teach.presenter.MinePresenter
import com.teach.guanjianhui.teachquality.widgets.SuspendDecoration
import kotlinx.android.synthetic.main.fragment_mine_test.*

/**
 * 我的模块
 * Created by guanjianhui on 18-1-29.
 */
//paging library宣布放弃,后会有期
class MineFragment : BaseFragment(), MineContract.View {


    private var mTitle: String? = null
    //   private val testViewModel: TestViewModel by lazy { ViewModelProviders.of(this).get(TestViewModel::class.java) }
    private lateinit var myAdapter: ListTestAdapter

    private lateinit var myLayoutManager: RecyclerView.LayoutManager

    private val mPresenter by lazy { MinePresenter() }

    override fun getLayoutId(): Int = R.layout.fragment_mine_test

    init {
        mPresenter.attachView(this)
    }

    override fun initView() {
//        testViewModel.getUserId().observe(this, (Observer { testReponseBean ->
//            if (testReponseBean != null) {
//
//            }
//        }
//                )
//        )
        myLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

    }

    override fun initData() {
        Toast.makeText(activity, "开始请求", Toast.LENGTH_SHORT).show()
        mPresenter.getData("4", 20)
    }

    override fun initListener() {

    }

    override fun detachView() {
        mPresenter.detachView()
    }

    companion object {
        fun getInstance(title: String): MineFragment {
            val fragment = MineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle//用于activity与fragment传递数据
            fragment.mTitle = title
            return fragment

        }
    }

    override fun loadSuccess(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun loadFailure(msg: String?) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun loadCompete(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }

    override fun setList(list: ArrayList<ListItemBean>) {
        myAdapter = ListTestAdapter(list, activity)
        rlv_mine.layoutManager = myLayoutManager

        rlv_mine.adapter = myAdapter

    }

}
