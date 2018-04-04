package com.teach.guanjianhui.teachquality.ui.teach.activity

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSmoothScroller
import android.support.v7.widget.ListPopupWindow
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseActivity
import com.teach.guanjianhui.teachquality.beans.EvaluateItemBean
import com.teach.guanjianhui.teachquality.beans.EvaluateStuBean
import com.teach.guanjianhui.teachquality.constant.Field
import com.teach.guanjianhui.teachquality.db.table.TeachTable
import com.teach.guanjianhui.teachquality.db.table.TeacherInfo_Table
import com.teach.guanjianhui.teachquality.ui.teach.adapter.EvaluateGroupAdapter
import com.teach.guanjianhui.teachquality.ui.teach.adapter.StudentEvAdapter
import com.teach.guanjianhui.teachquality.ui.teach.contract.EvaluateContract
import com.teach.guanjianhui.teachquality.ui.teach.presenter.EvaluatePresenter
import com.teach.guanjianhui.teachquality.utils.ToastUtils
import com.teach.guanjianhui.teachquality.widgets.SuspendDecoration
import kotlinx.android.synthetic.main.activity_studenteva.*
import kotlinx.android.synthetic.main.tool_title.*

/**
 * Created by guanjianhui on 18-3-23.
 */
class StudentEvaActivity : BaseActivity(), EvaluateContract.View {


    val mPresenter by lazy { EvaluatePresenter() }
    lateinit var list: List<EvaluateItemBean>
    lateinit var studentEvAdapter: StudentEvAdapter
    lateinit var studentManager: RecyclerView.LayoutManager
    lateinit var firstAdapter: EvaluateGroupAdapter
    lateinit var headViewList: ArrayList<View>
    val anim_roate_start by lazy { RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f) }
    val anim_roate_end by lazy { RotateAnimation(180f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f) }
    val myList: ArrayList<EvaluateStuBean> by lazy { ArrayList<EvaluateStuBean>() }
    var posTeacher = 0

    var teachingTaskNum: String? = null
    //学生编号
    var studentNum: String? = null

    //用来保存需要评价的教师的集合
    val teacherEvaList: ArrayList<ArrayList<EvaluateStuBean>> by lazy { ArrayList<ArrayList<EvaluateStuBean>>() }


    var isScroller = false
    val listPopupWindow by lazy { ListPopupWindow(this) }
    var teacherList: List<TeachTable.TeachingTask>? = null

    override fun initListener() {
        var smoothScroller = object : LinearSmoothScroller(this) {
            override fun getVerticalSnapPreference(): Int {
                return LinearSmoothScroller.SNAP_TO_START
            }
        }
        tool_title.setNavigationOnClickListener {
            finish()
        }

        lv_evaluate.setOnItemClickListener { parent, view, position, id ->
            isScroller = true
            if (isScroller) {
                when (position) {
                    0 -> {
                        smoothScroller.targetPosition = 0
                        changeTextColor(0)
                    }
                    1 -> {
                        smoothScroller.targetPosition = list[0].itemList.size
                        changeTextColor(1)
                    }
                    2 -> {
                        smoothScroller.targetPosition = list[0].itemList.size + list[1].itemList.size
                        changeTextColor(2)
                    }
                    3 -> {
                        smoothScroller.targetPosition = list[0].itemList.size + list[1].itemList.size + list[2].itemList.size
                        changeTextColor(3)
                    }
                    4 -> {
                        smoothScroller.targetPosition = list[0].itemList.size + list[1].itemList.size + list[2].itemList.size + list[3].itemList.size
                        changeTextColor(4)
                    }
                }
                studentManager.startSmoothScroll(smoothScroller)
            }
        }
        listPopupWindow.setOnItemClickListener { parent, view, position, id ->

            if (posTeacher != position) {
                tv_eva_teacher_name.text = getTeacherName(teacherList!![position].teacherNum)
                teachingTaskNum = teacherList!![position].teachingTaskNum
                posTeacher = position
                studentEvAdapter = StudentEvAdapter(this, teacherEvaList[position])
                rec_student_eva.adapter = studentEvAdapter
            }
            listPopupWindow.dismiss()


        }

        rec_student_eva.setOnTouchListener({ v, event ->
            isScroller = false

            false
        }
        )


        tv_end.setOnClickListener {
            if (posTeacher == -1) {
                ToastUtils.showToast(this, "已保存该教师评教信息，请选择下一个～")
            } else {
                for (item in teacherEvaList[posTeacher]) {
                    if (item.scoreType == 0x10) {
                        showErrorDialog()
                        return@setOnClickListener
                    }
                }
                mPresenter.saveTeacherEva(teacherEvaList[posTeacher], teachingTaskNum, studentNum)
            }
        }

        layout_eva_teacher_name.setOnClickListener {

            if (!listPopupWindow.isShowing) {
                mPresenter.getTeacherList()

            }
        }


    }

    //保存评价成功
    override fun saveEvaSuccess() {
        teacherEvaList.remove(teacherEvaList[posTeacher])//删除集合中已保存元素
        posTeacher = -1//重新置原位
        ToastUtils.showToast(this, "保存成功")
        if (tv_end.text == "完成") {
            startActivity(Intent(this, EvaFinishActivity::class.java))
            finish()
        }
    }

    private fun showListPop(names: Array<String?>) {


        listPopupWindow.setAdapter(ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, names))

        var height = 0
        // 对话框的宽高
        listPopupWindow.setWidth(350)

        if (names.size <= 5) {
            height = 170 * names.size
        } else {
            height = 850
        }

        listPopupWindow.height = height

        // ListPopupWindow的锚,弹出框的位置是相对当前View的位置
        listPopupWindow.anchorView = layout_eva_teacher_name

        // ListPopupWindow 距锚view的距离
        listPopupWindow.horizontalOffset = 50
        listPopupWindow.verticalOffset = 30

        listPopupWindow.isModal = false

        listPopupWindow.show()
        listPopupWindow.setOnDismissListener {
            im_eva_teacher.startAnimation(anim_roate_end)

        }

        im_eva_teacher.startAnimation(anim_roate_start)


    }

    private fun showErrorDialog() {
        MaterialDialog.Builder(this)
                .content("请检查确保已经选择全部分数项！！！")
                .positiveText("确定")
                .canceledOnTouchOutside(true)
                .onAny { dialog, which ->
                    if (which == DialogAction.POSITIVE) {
                        dialog.dismiss()
                    }
                }.show()
    }


    fun changeTextColor(pos: Int) {
        lv_evaluate.getChildAt(0).findViewById<ImageView>(R.id.im_eva_first).setImageResource(R.mipmap.ic_eva_attitude)
        lv_evaluate.getChildAt(1).findViewById<ImageView>(R.id.im_eva_first).setImageResource(R.mipmap.ic_eva_content)
        lv_evaluate.getChildAt(2).findViewById<ImageView>(R.id.im_eva_first).setImageResource(R.mipmap.ic_eva_method)
        lv_evaluate.getChildAt(3).findViewById<ImageView>(R.id.im_eva_first).setImageResource(R.mipmap.ic_eva_effect)
        lv_evaluate.getChildAt(4).findViewById<ImageView>(R.id.im_eva_first).setImageResource(R.mipmap.ic_eva_order)

        when (pos) {
            0 -> lv_evaluate.getChildAt(pos).findViewById<ImageView>(R.id.im_eva_first).setImageResource(R.mipmap.ic_eva_attitude_green)

            1 -> lv_evaluate.getChildAt(pos).findViewById<ImageView>(R.id.im_eva_first).setImageResource(R.mipmap.ic_eva_content_green)

            2 -> lv_evaluate.getChildAt(pos).findViewById<ImageView>(R.id.im_eva_first).setImageResource(R.mipmap.ic_eva_method_green)

            3 -> lv_evaluate.getChildAt(pos).findViewById<ImageView>(R.id.im_eva_first).setImageResource(R.mipmap.ic_eva_effect_green)

            4 -> lv_evaluate.getChildAt(pos).findViewById<ImageView>(R.id.im_eva_first).setImageResource(R.mipmap.ic_eva_order_green)

        }

        for (i in 0..4) {
            if (i == pos) {
                lv_evaluate.getChildAt(i).findViewById<TextView>(R.id.tv_eva_first).setTextColor(ContextCompat.getColor(this, R.color.clo_status_dark_green))

            } else {
                lv_evaluate.getChildAt(i).findViewById<TextView>(R.id.tv_eva_first).setTextColor(ContextCompat.getColor(this, R.color.clo_dark_gray))
            }
        }
    }

    override fun initView() {
        mPresenter.attachView(this)
        container_view.visibility = View.INVISIBLE
        studentManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        tv_title.text = "学生评分"

        headViewList = ArrayList()

        anim_roate_start.duration = 500
        anim_roate_start.fillAfter = true //执行完停留
        anim_roate_end.duration = 500
        anim_roate_end.fillAfter = true //执行完停留

    }

    override fun initData() {
        studentNum = intent.getStringExtra("studentNum")
        mPresenter.getData()
        mPresenter.getScore()
    }

    override fun layoutId(): Int {
        return R.layout.activity_studenteva
    }

    override fun detachView() {
        mPresenter.detachView()
    }

    //请求未评价老师数据成功
    override fun loadTeacherSuccess(list: List<TeachTable.TeachingTask>) {
        if (list.size > 0) {//返回数据大于0才进行评价
            teacherList = list
            var names: Array<String?> = arrayOfNulls<String>(list.size)
            for (i in 0..(list.size - 1)) {
                names[i] = getTeacherName(list[i].teacherNum)
            }


            //第一次进入评分界面
            if (teacherEvaList.size == 0) {
                for (items in list) {
                    val dataList = ArrayList<EvaluateStuBean>()
                    for (item in myList) {
                        dataList.add(EvaluateStuBean(item.num, item.evaluate, item.title, item.type))
                    }
                    teacherEvaList.add(dataList)
                }
                studentEvAdapter = StudentEvAdapter(this, teacherEvaList[posTeacher])
                rec_student_eva.adapter = studentEvAdapter
                tv_eva_teacher_name.text = getTeacherName(list[posTeacher].teacherNum)
                teachingTaskNum = list[posTeacher].teachingTaskNum
                container_view.visibility = View.VISIBLE
            } else {
                showListPop(names)
            }
            if (teacherEvaList.size > 1) {
                tv_end.setText("保存")
            } else {
                tv_end.setText("完成")
            }
        } else {
            startActivity(Intent(this, EvaFinishActivity::class.java))
            finish()
        }
    }

    /**
     * 根据教师编号获取名字
     */
    private fun getTeacherName(teachNum: String): String {
        return SQLite.select(TeacherInfo_Table.name).from(TeachTable.TeacherInfo::class.java).where(TeacherInfo_Table.teacherNum.`is`(teachNum)).querySingle()!!.name
    }

    override fun loadSuccess(list: List<EvaluateItemBean>) {
        this.list = list

        if (myList.size == 0) {
            for (items in list) {

                for (item in items.itemList) {
                    myList.add(EvaluateStuBean(item.num, item.evaluate, items.title, items.type))
                }

                var headView = LayoutInflater.from(this@StudentEvaActivity).inflate(R.layout.item_student_eva, null)
                var im_student_eva = headView.findViewById<ImageView>(R.id.im_student_eva)
                var tv_student_eva = headView.findViewById<TextView>(R.id.tv_student_eva)

                when (items.type) {
                    Field.EVALUATE_ATTITUDE -> {
                        im_student_eva.setImageResource(R.mipmap.ic_eva_attitude_white)
                        tv_student_eva.setText(items.title)
                    }
                    Field.EVALUATE_CONTENT -> {
                        im_student_eva.setImageResource(R.mipmap.ic_eva_content_white)
                        tv_student_eva.setText(items.title)
                    }
                    Field.EVALUATE_METHOD -> {
                        im_student_eva.setImageResource(R.mipmap.ic_eva_method_white)
                        tv_student_eva.setText(items.title)
                    }
                    Field.EVALUATE_EFFECT -> {
                        im_student_eva.setImageResource(R.mipmap.ic_eva_effect_white)
                        tv_student_eva.setText(items.title)
                    }
                    Field.EVALUATE_ORDER -> {
                        im_student_eva.setImageResource(R.mipmap.ic_eva_order_white)
                        tv_student_eva.setText(items.title)
                    }
                }

                headViewList.add(headView)
            }
        }

        var curType = 0x00
        var suspendDecoration = SuspendDecoration(object : SuspendDecoration.SuspendGroupListener {
            override fun getTopPos(position: Int) {

                if (!isScroller) {

                    if (myList[position].type != curType) {
                        curType = myList[position].type
                        when (myList[position].type) {
                            Field.EVALUATE_ATTITUDE -> {
                                changeTextColor(0)
                            }
                            Field.EVALUATE_CONTENT -> {
                                changeTextColor(1)
                            }
                            Field.EVALUATE_METHOD -> {
                                changeTextColor(2)
                            }
                            Field.EVALUATE_EFFECT -> {
                                changeTextColor(3)
                            }
                            Field.EVALUATE_ORDER -> {
                                changeTextColor(4)
                            }
                        }
                    }

                }
            }

            override fun getGroupName(position: Int): String? {

                return myList[position].title
            }

            override fun getGroupView(position: Int): View? {

                when (myList[position].type) {
                    Field.EVALUATE_ATTITUDE -> {
                        return headViewList[0]
                    }
                    Field.EVALUATE_CONTENT -> {
                        return headViewList[1]
                    }
                    Field.EVALUATE_METHOD -> {
                        return headViewList[2]
                    }
                    Field.EVALUATE_EFFECT -> {
                        return headViewList[3]
                    }
                    Field.EVALUATE_ORDER -> {
                        return headViewList[4]
                    }
                }

                return null
            }
        })

        // studentEvAdapter = StudentEvAdapter(this, teacherEvaList[0])
        rec_student_eva.layoutManager = studentManager

        rec_student_eva.addItemDecoration(suspendDecoration)
        //rec_student_eva.adapter = studentEvAdapter

        firstAdapter = EvaluateGroupAdapter(this, list)
        lv_evaluate.adapter = firstAdapter

        mPresenter.getTeacherList()
    }

    override fun loadFailure(msg: String) {
    }

}
