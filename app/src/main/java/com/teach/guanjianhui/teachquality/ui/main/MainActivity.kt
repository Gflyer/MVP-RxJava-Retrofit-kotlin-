package com.teach.guanjianhui.teachquality.ui.main


import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.FragmentTransaction
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.raizlabs.android.dbflow.config.FlowManager
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseActivity
import com.teach.guanjianhui.teachquality.beans.TabEntity
import com.teach.guanjianhui.teachquality.ui.teach.fragment.DiscoveryFragment
import com.teach.guanjianhui.teachquality.ui.teach.fragment.HomeFragment
import com.teach.guanjianhui.teachquality.ui.teach.fragment.HotFragment
import com.teach.guanjianhui.teachquality.ui.teach.fragment.MineFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {


    //底部栏文字
    private val tab_tittles: Array<String> = arrayOf("每日精选", "发现", "热门", "我的")

    //正常选择图片
    private val normal_icons: IntArray = intArrayOf(R.mipmap.ic_discovery_normal, R.mipmap.ic_home_normal, R.mipmap.ic_hot_normal, R.mipmap.ic_mine_normal)

    //被选中图片
    private val selected_icons: IntArray = intArrayOf(R.mipmap.ic_discovery_selected, R.mipmap.ic_home_selected, R.mipmap.ic_hot_selected, R.mipmap.ic_mine_selected)

    //存放customtabentities集合元素
    private val tab_entities: ArrayList<CustomTabEntity> = ArrayList()

    private var mIndex: Int = 0

    var homeFragment: HomeFragment? = null
    var hotFragment: HotFragment? = null
    var discoveryFragment: DiscoveryFragment? = null
    var mineFragment: MineFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initTab()

        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currState", 0)
        }
        tab_layout.currentTab = mIndex//设置底部栏当前的item
        switchFragment(mIndex)
    }

    //防止app突然crash掉
    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState?.putInt("currState", mIndex)
    }

    /*
    * 初始化底部栏
    * */
    private fun initTab() {
        //mapto将{}的东西append到（）的集合中
        (0 until tab_tittles.size).mapTo(tab_entities) { TabEntity(tab_tittles[it], normal_icons[it], selected_icons[it]) }

        tab_layout.setTabData(tab_entities)
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                //切换fragment
                switchFragment(position)
            }

            override fun onTabReselect(position: Int) {

            }
        })

    }

    /*
    * 切换选择fragment
    * */
    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        //隐藏所有的fragment
        hideFragments(transaction)
        when (position) {
            0//home
            -> homeFragment?.let { transaction.show(it) }
                    ?: HomeFragment.getInstance(tab_tittles[position]).let {
                        homeFragment = it
                        transaction.add(R.id.fl_fragment, it, "home")
                    }
            1//discovery
            -> discoveryFragment?.let { transaction.show(it) }
                    ?: DiscoveryFragment.getInstance(tab_tittles[position]).let {
                        discoveryFragment = it
                        transaction.add(R.id.fl_fragment, it, "discovery")
                    }
            2//hot
            -> hotFragment?.let { transaction.show(it) }
                    ?: HotFragment.getInstance(tab_tittles[position]).let {
                        hotFragment = it
                        transaction.add(R.id.fl_fragment, it, "hot")
                    }
            3//mine
            -> mineFragment?.let { transaction.show(it) }
                    ?: MineFragment.getInstance(tab_tittles[position]).let {
                        mineFragment = it
                        transaction.add(R.id.fl_fragment, it, "mine")
                    }
        }

        mIndex = position
        tab_layout.currentTab = mIndex
        transaction.commitAllowingStateLoss()//使用onInsaveInstance（）方法不能使用commit
    }

    private fun hideFragments(transaction: FragmentTransaction) {
        homeFragment?.let { transaction.hide(it) }
        discoveryFragment?.let { transaction.hide(it) }
        hotFragment?.let { transaction.hide(it) }
        mineFragment?.let { transaction.hide(it) }

    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
    }

    override fun initData() {
    }

    override fun initListener() {
    }
    override fun detachView() {

    }
}
