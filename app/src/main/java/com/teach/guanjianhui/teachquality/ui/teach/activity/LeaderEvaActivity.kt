package com.teach.guanjianhui.teachquality.ui.teach.activity

import android.content.Intent
import android.support.v7.widget.ListPopupWindow
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ArrayAdapter
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseActivity
import com.teach.guanjianhui.teachquality.beans.EvaTeachItem
import com.teach.guanjianhui.teachquality.beans.EvaTeachersBeans
import com.teach.guanjianhui.teachquality.constant.Field
import com.teach.guanjianhui.teachquality.db.table.Syllabus_Table
import com.teach.guanjianhui.teachquality.db.table.TeachTable
import com.teach.guanjianhui.teachquality.db.table.TeacherInfo_Table
import com.teach.guanjianhui.teachquality.ui.teach.contract.LeaderEvaContact
import com.teach.guanjianhui.teachquality.ui.teach.presenter.LeaderEvaPresenter
import com.teach.guanjianhui.teachquality.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_leadereva.*
import kotlinx.android.synthetic.main.tool_title.*

/**
 * 同行(督导评价activity）
 * Created by guanjianhui on 18-3-28.
 */
class LeaderEvaActivity : BaseActivity(), LeaderEvaContact.View {


    var evaTypeNum: String? = null//教师编号
    var type: Int = 0
    val mPresenter: LeaderEvaPresenter by lazy { LeaderEvaPresenter() }
    var evaTeacherList: ArrayList<EvaTeachersBeans>? = null
    var listPopupWindowT: ListPopupWindow? = null//教师pop
    var listPopupWindowC: ListPopupWindow? = null //课程pop
    val anim_roate_start by lazy { RotateAnimation(0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f) }
    val anim_roate_end by lazy { RotateAnimation(180f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f) }
    var curPosition = 0
    var coursePosition = 0


    override fun layoutId(): Int {
        return R.layout.activity_leadereva
    }

    override fun initView() {
        mPresenter.attachView(this)
        listPopupWindowT = ListPopupWindow(this)
        listPopupWindowC = ListPopupWindow(this)
        tv_title.text = "同行评分"
        tv_end.text = "查看"
        tv_total_score.text = "0"
        et_eva_attitude.requestFocus()

        anim_roate_start.duration = 500
        anim_roate_start.fillAfter = true //执行完停留
        anim_roate_end.duration = 500
        anim_roate_end.fillAfter = true //执行完停留
    }

    override fun initData() {
        evaTypeNum = intent.getStringExtra("evaTypeNum")
        type = intent.getIntExtra("type", 0)

        if (type == Field.LOGIN_TEACHER) {
            mPresenter.getTeacherEvaData()
        } else if (type == Field.LOGIN_SUPERVISOR) {
            mPresenter.getSupervisorEvaData()
        }
    }

    override fun initListener() {
        tv_end.setOnClickListener {
            startActivity(Intent(this, EvaDetialActivity::class.java))
        }
        //teacherList选择点击
        layout_eva_name.setOnClickListener {
            var names: Array<String?> = arrayOfNulls(evaTeacherList!!.size)
            for ((index, item) in evaTeacherList!!.withIndex()) {
                names[index] = item.teacherName
            }
            showTeacherListPop(names)
        }

        listPopupWindowT!!.setOnItemClickListener { parent, view, position, id ->
            curPosition = position
            tv_eva_name.text = evaTeacherList!![position].teacherName
            listPopupWindowT!!.dismiss()
            initCourse(position)
        }

        //courseList点击选择
        layout_eva_course_name.setOnClickListener {
            var courses: Array<String?> = arrayOfNulls(evaTeacherList!![curPosition].list.size)
            for ((index, item) in evaTeacherList!![curPosition].list.withIndex()) {
                courses[index] = item.courseName
            }
            showCourseListPop(courses)
        }

        listPopupWindowC!!.setOnItemClickListener { parent, view, position, id ->
            coursePosition = position
            tv_eva_course_name.text = evaTeacherList!![curPosition].list[position].courseName
            listPopupWindowC!!.dismiss()
            clearScore()
        }

        listPopupWindowC!!.setOnDismissListener {
            im_eva_course.startAnimation(anim_roate_end)
        }

        listPopupWindowT!!.setOnDismissListener {
            im_eva_leader.startAnimation(anim_roate_end)

        }

        //保存
        btn_eva_save.setOnClickListener {
            checkScore()
        }

        et_eva_attitude.addTextChangedListener(object : TextWatcher {
            var preNum = ""
            var curNum = ""
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                preNum = s.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                curNum = s.toString()
                if (!TextUtils.isEmpty(curNum)) {
                    if (!(curNum.toInt() in 0..20)) {
                        et_eva_attitude.setText(preNum)
                        ToastUtils.showToast(this@LeaderEvaActivity, "请输入指定范围的数")
                    } else {
                        setTotal()
                    }
                }
            }
        })

        et_eva_content.addTextChangedListener(object : TextWatcher {
            var preNum = ""
            var curNum = ""
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                preNum = s.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                curNum = s.toString()
                if (!TextUtils.isEmpty(curNum)) {
                    if (!(curNum.toInt() in 0..20)) {
                        et_eva_content.setText(preNum)
                        ToastUtils.showToast(this@LeaderEvaActivity, "请输入指定范围的数")
                    } else {
                        setTotal()
                    }
                }
            }
        })

        et_eva_method.addTextChangedListener(object : TextWatcher {
            var preNum = ""
            var curNum = ""
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                preNum = s.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                curNum = s.toString()
                if (!TextUtils.isEmpty(curNum)) {
                    if (!(curNum.toInt() in 0..30)) {
                        et_eva_method.setText(preNum)
                        ToastUtils.showToast(this@LeaderEvaActivity, "请输入指定范围的数")
                    } else {
                        setTotal()
                    }
                }
            }
        })

        et_eva_effect.addTextChangedListener(object : TextWatcher {
            var preNum = ""
            var curNum = ""
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                preNum = s.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                curNum = s.toString()
                if (!TextUtils.isEmpty(curNum)) {
                    if (!(curNum.toInt() in 0..20)) {
                        et_eva_effect.setText(preNum)
                        ToastUtils.showToast(this@LeaderEvaActivity, "请输入指定范围的数")
                    } else {
                        setTotal()
                    }
                }
            }
        })

        et_eva_order.addTextChangedListener(object : TextWatcher {
            var preNum = ""
            var curNum = ""
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                preNum = s.toString()
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                curNum = s.toString()
                if (!TextUtils.isEmpty(curNum)) {
                    if (!(curNum.toInt() in 0..10)) {
                        et_eva_order.setText(preNum)
                        ToastUtils.showToast(this@LeaderEvaActivity, "请输入指定范围的数")
                    } else {
                        setTotal()
                    }
                }
            }
        })

        tool_title.setNavigationOnClickListener {
            finish()
        }


    }

    private fun setTotal() {
        var totalScore = 0
        if (!TextUtils.isEmpty(et_eva_attitude.text)) {
            totalScore += et_eva_attitude.text.toString().trim().toInt()
        }
        if (!TextUtils.isEmpty(et_eva_content.text)) {
            totalScore += et_eva_content.text.toString().trim().toInt()
        }
        if (!TextUtils.isEmpty(et_eva_method.text)) {
            totalScore += et_eva_method.text.toString().trim().toInt()
        }
        if (!TextUtils.isEmpty(et_eva_effect.text)) {
            totalScore += et_eva_effect.text.toString().trim().toInt()
        }
        if (!TextUtils.isEmpty(et_eva_order.text)) {
            totalScore += et_eva_order.text.toString().trim().toInt()
        }

        tv_total_score.text = totalScore.toString()

    }


    private fun checkScore() {
        val attitude_score = et_eva_attitude.text.toString().trim()
        val content_score = et_eva_content.text.toString().trim()
        val method_score = et_eva_method.text.toString().trim()
        val effect_score = et_eva_effect.text.toString().trim()
        val order_score = et_eva_order.text.toString().trim()

        if (TextUtils.isEmpty(attitude_score) || TextUtils.isEmpty(content_score) || TextUtils.isEmpty(method_score) || TextUtils.isEmpty(effect_score) || TextUtils.isEmpty(order_score)) {
            ToastUtils.showToast(this, "请检查确保全部分数项已填写")
            return
        }
        if (attitude_score.toInt() in 0..20 && content_score.toInt() in 0..20 && method_score.toInt() in 0..30 &&
                effect_score.toInt() in 0..20 && order_score.toInt() in 0..10) {
            evaTeacherList!![curPosition].list[coursePosition].let {
                it.attitudeScore = attitude_score.toInt()
                it.contentScore = content_score.toInt()
                it.methodScore = method_score.toInt()
                it.effectScore = effect_score.toInt()
                it.orderScore = order_score.toInt()
                it.comment = ""
            }
            if (type == Field.LOGIN_TEACHER) {
                mPresenter.saveTeacherScore(evaTypeNum!!, evaTeacherList!![curPosition].list[coursePosition])
            } else if (type == Field.LOGIN_SUPERVISOR) {
                mPresenter.saveSupervisorScore(evaTypeNum!!, evaTeacherList!![curPosition].list[coursePosition])
            }
            changeListData()
        } else {
            ToastUtils.showToast(this, "请输入指定范围内的分数")
        }

    }

    private fun changeListData() {
        var courseCount = evaTeacherList!![curPosition].list.size
        var teacherCount = evaTeacherList!!.size
        if (--courseCount > 0) {//删除一条后仍然存在
            evaTeacherList!![curPosition].list.removeAt(coursePosition)

            if (coursePosition >= courseCount) {
                coursePosition = 0
            }
            tv_eva_course_name.text = evaTeacherList!![curPosition].list[coursePosition].courseName
        } else {
            if (--teacherCount > 0) {

                evaTeacherList!!.removeAt(curPosition)
                coursePosition = 0
                if (curPosition >= teacherCount) {
                    curPosition = 0
                }

                tv_eva_course_name.text = evaTeacherList!![curPosition].list[coursePosition].courseName
                tv_eva_name.text = evaTeacherList!![curPosition].teacherName

            } else {
                startActivity(Intent(this, EvaFinishActivity::class.java))
                finish()

            }
        }
        clearScore()
    }


    private fun initCourse(pos: Int) {
        coursePosition = 0
        tv_eva_course_name.text = evaTeacherList!![pos].list[coursePosition].courseName
        clearScore()
    }

    private fun clearScore() {
        et_eva_attitude.setText("")
        et_eva_content.setText("")
        et_eva_method.setText("")
        et_eva_effect.setText("")
        et_eva_order.setText("")
        tv_total_score.text = "0"

    }

    override fun detachView() {
        //listPopupWindowT.clearListSelection()
        if (listPopupWindowC != null) {
            listPopupWindowC!!.dismiss()
            listPopupWindowC = null
        }
        if (listPopupWindowT != null) {
            listPopupWindowT!!.dismiss()
            listPopupWindowT = null
        }
        mPresenter.detachView()
    }

    override fun saveEvaSuccess() {
        ToastUtils.showToast(this, "保存成功")
    }

    override fun loadTeacherSuccess(teacherNameList: ArrayList<TeachTable.TeachingTask>) {

        if (teacherNameList.size > 0) {
            //第一次进入界面请求
            if (evaTeacherList == null) {
                evaTeacherList = ArrayList()
                for (item in teacherNameList) {
                    //   Log.i("guan5",item.teacherNum)
                    val pos = getContainIndex(item.teacherNum)//获取对应的item位置

                    if (pos == -1) {
                        var evaTeachItemList = ArrayList<EvaTeachItem>()
                        evaTeachItemList.add(EvaTeachItem(item.teachingTaskNum, item.dyllabusNum, getCourseName(item.dyllabusNum), comment = ""))
                        evaTeacherList!!.add(EvaTeachersBeans(item.teacherNum, getTeacherName(item.teacherNum), evaTeachItemList))
                        //Log.i("guan4",getTeacherName(item.teacherNum))
                    } else {
                        evaTeacherList!![pos].list.add(EvaTeachItem(item.teachingTaskNum, item.dyllabusNum, getCourseName(item.dyllabusNum), comment = ""))
                    }
                }

                tv_eva_name.text = evaTeacherList!![curPosition].teacherName
                initCourse(curPosition)

            } else {//不是第一次请求

            }
        } else {
            startActivity(Intent(this, EvaFinishActivity::class.java))
            finish()
        }
    }

    private fun showTeacherListPop(names: Array<String?>) {


        listPopupWindowT!!.setAdapter(ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, names))

        var height = 0
        // 对话框的宽高
        listPopupWindowT!!.width = 350

        if (names.size <= 5) {
            height = 170 * names.size
        } else {
            height = 850
        }

        listPopupWindowT!!.height = height

        // ListPopupWindow的锚,弹出框的位置是相对当前View的位置
        listPopupWindowT!!.anchorView = layout_eva_name

        // ListPopupWindow 距锚view的距离
        listPopupWindowT!!.horizontalOffset = 50
        listPopupWindowT!!.verticalOffset = 30

        listPopupWindowT!!.isModal = false

        listPopupWindowT!!.show()


        im_eva_leader.startAnimation(anim_roate_start)

    }

    private fun showCourseListPop(courses: Array<String?>) {
        listPopupWindowC!!.setAdapter(ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, courses))

        var height = 0
        // 对话框的宽高
        listPopupWindowC!!.width = 350

        if (courses.size <= 5) {
            height = 170 * courses.size
        } else {
            height = 850
        }

        listPopupWindowC!!.height = height

        listPopupWindowC!!.anchorView = layout_eva_course_name

        listPopupWindowC!!.horizontalOffset = 0
        listPopupWindowC!!.verticalOffset = 30

        listPopupWindowC!!.isModal = false

        listPopupWindowC!!.show()

        im_eva_course.startAnimation(anim_roate_start)

    }

    private fun getCourseName(courseNum: String): String {
        return SQLite.select(Syllabus_Table.courseName).from(TeachTable.Syllabus::class.java).where(Syllabus_Table.courseNum.`is`(courseNum)).querySingle()!!.courseName
    }

    /**
     * 获取相等的位置
     */
    private fun getContainIndex(teachNum: String): Int {
        for ((index, item) in evaTeacherList!!.withIndex()) {
            if (teachNum == item.teacherNum) {
                return index
            }
        }
        return -1
    }

    /**
     * 根据教师编号获取名字
     */
    private fun getTeacherName(teachNum: String): String {
        return SQLite.select(TeacherInfo_Table.name).from(TeachTable.TeacherInfo::class.java).where(TeacherInfo_Table.teacherNum.`is`(teachNum)).querySingle()!!.name
    }

    override fun loadFailure(msg: String) {
        ToastUtils.showToast(this, msg)
    }

}