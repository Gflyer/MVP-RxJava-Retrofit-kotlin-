package com.teach.guanjianhui.teachquality.ui.teach.activity

import android.support.v4.content.ContextCompat
import com.teach.guanjianhui.teachquality.R
import com.teach.guanjianhui.teachquality.base.BaseActivity
import kotlinx.android.synthetic.main.tool_title.*
import android.view.Gravity
import android.content.Context
import android.content.Intent
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.afollestad.materialdialogs.DialogAction
import com.afollestad.materialdialogs.MaterialDialog
import com.teach.guanjianhui.teachquality.constant.Field
import com.teach.guanjianhui.teachquality.db.table.TeachTable
import com.teach.guanjianhui.teachquality.ui.teach.contract.LoginContact
import com.teach.guanjianhui.teachquality.ui.teach.presenter.LoginPresenter
import com.teach.guanjianhui.teachquality.utils.SPUtils
import com.teach.guanjianhui.teachquality.utils.ToastUtils
import kotlinx.android.synthetic.main.activity_login.*


/**
 * Created by guanjianhui on 18-3-15.
 */
class LoginActivity : BaseActivity(), LoginContact.View {


    val tv_myTittle: TextView by lazy { TextView(this) }

    val mPresenter: LoginPresenter by lazy { LoginPresenter() }

    var type = SPUtils.getInt(Field.LOGIN_STATUS, Field.LOGIN_STUDENT)

    var studentNum: String? = null//学生编号
    var teacherNum: String? = null//教师编号
    //var workmateNum: String? = null//同行编号
    var supervisorNum: String? = null//督导编号
    var managerNum: String? = null//管理员编号


    override fun layoutId(): Int {
        return R.layout.activity_login
    }

    override fun initView() {

        mPresenter.attachView(this)
        tv_myTittle.setTextColor(ContextCompat.getColor(this, R.color.clo_normal_white))
        tv_myTittle.text = "登录"
        tv_myTittle.setTextSize(20.0f)
        ToolbarHelper.addMiddleTitle(tv_myTittle, tool_title)
        tool_title.setTitleTextColor(ContextCompat.getColor(this, R.color.clo_normal_white))
        layout_statusBar.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_toolbar_head))
        view_statusBar.layoutParams.height = getStatusBarHeight()

        val version = packageManager.getPackageInfo(packageName, 0).versionName
        val headerLayout = navigation_view.getHeaderView(0)
        val tv_version = headerLayout.findViewById<TextView>(R.id.tv_version)

        tv_version.text = "v $version"

        //隐藏
        tv_title.visibility = View.GONE
        tv_end.visibility = View.GONE

        initLogin()

    }

    //初始化登录选项
    private fun initLogin() {
        cb_remember.isChecked = SPUtils.getBoolean(Field.IS_REM, false)

        when (type) {
            Field.LOGIN_STUDENT -> {
                tv_login_type.setText("学生")
                im_login.setImageResource(R.mipmap.ic_big_student)
                if (SPUtils.getBoolean(Field.IS_REM, false)) {
                    ed_username.setText(SPUtils.getString(Field.REM_STUDENT_USERNAME, ""))
                    ed_password.setText(SPUtils.getString(Field.REM_STUDENT_PASSWORD, ""))
                }
            }

            Field.LOGIN_TEACHER -> {
                tv_login_type.setText("教师")
                im_login.setImageResource(R.mipmap.ic_big_teacher)
                if (SPUtils.getBoolean(Field.IS_REM, false)) {
                    ed_username.setText(SPUtils.getString(Field.REM_TEACHER_USERNAME, ""))
                    ed_password.setText(SPUtils.getString(Field.REM_TEACHER_PASSWORD, ""))
                }
            }

            Field.LOGIN_SUPERVISOR -> {
                tv_login_type.setText("督导")
                im_login.setImageResource(R.mipmap.ic_big_supervisor)
                if (SPUtils.getBoolean(Field.IS_REM, false)) {
                    ed_username.setText(SPUtils.getString(Field.REM_SUPERVISOR_USERNAME, ""))
                    ed_password.setText(SPUtils.getString(Field.REM_SUPERVISOR_PASSWORD, ""))
                }
            }

            Field.LOGIN_Manager -> {
                tv_login_type.setText("管理员")
                im_login.setImageResource(R.mipmap.ic_big_manager)
                if (SPUtils.getBoolean(Field.IS_REM, false)) {
                    ed_username.setText(SPUtils.getString(Field.REM_MANAGER_USERNAME, ""))
                    ed_password.setText(SPUtils.getString(Field.REM_MANAGER_PASSWORD, ""))
                }
            }
        }
    }

    override fun initData() {

    }


    override fun initListener() {

        //箭头点击
        tool_title.setNavigationOnClickListener {
            if (drawLayout.isDrawerOpen(navigation_view)) {
                drawLayout.closeDrawer(navigation_view)
            } else {
                drawLayout.openDrawer(navigation_view)
            }
        }

        //侧边栏滑动监听
        var toggle = object : ActionBarDrawerToggle(this, drawLayout, tool_title, R.string.app_name, R.string.app_name) {

            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                super.onDrawerSlide(drawerView, slideOffset)
                val imm = application.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(this@LoginActivity.currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
                //imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                tv_myTittle.text = "关于"
            }

            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                tv_myTittle.text = "登录"

            }
        }

        drawLayout.addDrawerListener(toggle)
        toggle.syncState()

        //侧边栏点击事件监听
        navigation_view.setNavigationItemSelectedListener { item ->
            //Toast.makeText(this, "" + item.itemId, Toast.LENGTH_SHORT).show()
            when (item.itemId) {
            //学生
                R.id.nav_student -> {
                    tv_login_type.setText("学生")
                    im_login.setImageResource(R.mipmap.ic_big_student)
                    drawLayout.closeDrawer(navigation_view)
                    if (type != Field.LOGIN_STUDENT) {
                        clearText()
                        type = Field.LOGIN_STUDENT
                        SPUtils.putInt(Field.LOGIN_STATUS, Field.LOGIN_STUDENT)


                        if (SPUtils.getBoolean(Field.IS_REM, false)) {
                            ed_username.setText(SPUtils.getString(Field.REM_STUDENT_USERNAME, ""))
                            ed_password.setText(SPUtils.getString(Field.REM_STUDENT_PASSWORD, ""))
                        }
                    }
                }

            //教师
                R.id.nav_teacher -> {
                    tv_login_type.setText("教师")
                    im_login.setImageResource(R.mipmap.ic_big_teacher)
                    drawLayout.closeDrawer(navigation_view)
                    if (type != Field.LOGIN_TEACHER) {
                        clearText()
                        type = Field.LOGIN_TEACHER
                        SPUtils.putInt(Field.LOGIN_STATUS, Field.LOGIN_TEACHER)

                        if (SPUtils.getBoolean(Field.IS_REM, false)) {
                            ed_username.setText(SPUtils.getString(Field.REM_TEACHER_USERNAME, ""))
                            ed_password.setText(SPUtils.getString(Field.REM_TEACHER_PASSWORD, ""))
                        }
                    }
                }

            //督导
                R.id.nav_supervisor -> {
                    tv_login_type.setText("督导")
                    im_login.setImageResource(R.mipmap.ic_big_supervisor)
                    drawLayout.closeDrawer(navigation_view)
                    if (type != Field.LOGIN_SUPERVISOR) {
                        clearText()
                        type = Field.LOGIN_SUPERVISOR
                        SPUtils.putInt(Field.LOGIN_STATUS, Field.LOGIN_SUPERVISOR)

                        if (SPUtils.getBoolean(Field.IS_REM, false)) {
                            ed_username.setText(SPUtils.getString(Field.REM_SUPERVISOR_USERNAME, ""))
                            ed_password.setText(SPUtils.getString(Field.REM_SUPERVISOR_PASSWORD, ""))
                        }
                    }
                }

            //同行
                R.id.nav_manager -> {
                    tv_login_type.setText("管理员")
                    im_login.setImageResource(R.mipmap.ic_big_manager)
                    drawLayout.closeDrawer(navigation_view)
                    if (type != Field.LOGIN_Manager) {
                        clearText()
                        type = Field.LOGIN_Manager
                        SPUtils.putInt(Field.LOGIN_STATUS, Field.LOGIN_Manager)

                        if (SPUtils.getBoolean(Field.IS_REM, false)) {
                            ed_username.setText(SPUtils.getString(Field.REM_MANAGER_USERNAME, ""))
                            ed_password.setText(SPUtils.getString(Field.REM_MANAGER_PASSWORD, ""))
                        }
                    }
                }


            //设置
                R.id.nav_setting -> {
                    drawLayout.closeDrawer(navigation_view)
                    ToastUtils.showToast(this,"开发中")
                }

            //更新
                R.id.nav_update -> {
                    drawLayout.closeDrawer(navigation_view)
                    ToastUtils.showToast(this,"开发中")
                }


            }
            true

        }

        //登录按钮点击
        btn_sign_in.setOnClickListener {
            when (type) {
                Field.LOGIN_STUDENT -> mPresenter.getStudentUserList()
                Field.LOGIN_TEACHER -> mPresenter.getTeacherUserList()
                Field.LOGIN_SUPERVISOR -> mPresenter.getSuperVisorUserList()
                Field.LOGIN_Manager -> mPresenter.getManagerUserList()

            }
        }

        //ed监听
        ed_username.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!TextUtils.isEmpty(s)) {
                    textlayout_username.error = ""
                    textlayout_username.isErrorEnabled = false
                }
            }
        })

        ed_password.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!TextUtils.isEmpty(s)) {
                    textlayout_password.error = ""
                    textlayout_password.isErrorEnabled = false
                }
            }

        })


    }

    fun clearText() {
        ed_username.setText("")
        ed_password.setText("")
        ed_username.requestFocus()
        textlayout_username.error = ""
        textlayout_username.isErrorEnabled = false
        textlayout_password.error = ""
        textlayout_password.isErrorEnabled = false
    }

    override fun detachView() {
        mPresenter.detachView()
    }


    override fun loadStudentSuccess(list: List<TeachTable.StudentUser>) {
        if (!isEmpty()) {
            for ((index, item) in list.withIndex()) {

                if (item.studentNum.equals(ed_username.text.toString().trim()) && item.password.equals(ed_password.text.toString().trim())) {
                    //登陆成功
                    ToastUtils.showToast(this, "登录成功")
                    showPromiseDialog()
                    studentNum = item.studentNum
                    if (cb_remember.isChecked) {
                        SPUtils.putString(Field.REM_STUDENT_USERNAME, ed_username.text.toString().trim())
                        SPUtils.putString(Field.REM_STUDENT_PASSWORD, ed_password.text.toString().trim())
                        SPUtils.putBoolean(Field.IS_REM, true)
                    } else {
                        SPUtils.putBoolean(Field.IS_REM, false)
                    }
                    break
                }
                if (index == list.size - 1) {
                    //登录失败
                    ToastUtils.showToast(this, "登录失败,请检查用户名或密码")
                }
            }

        }
    }

    override fun loadTeacherSuccess(list: List<TeachTable.TeacherUser>) {
        if (!isEmpty()) {
            for ((index, item) in list.withIndex()) {
                if (item.teacherNum.equals(ed_username.text.toString().trim()) && item.password.equals(ed_password.text.toString().trim())) {
                    //登陆成功
                    ToastUtils.showToast(this, "登录成功")

                    //跳转同行评价界面
                    teacherNum = item.teacherNum
                    val teacherIntent = Intent(this, TeacherChooseActivity::class.java)
                    teacherIntent.putExtra("teacherNum", teacherNum)
                    startActivity(teacherIntent)

                    if (cb_remember.isChecked) {
                        SPUtils.putString(Field.REM_TEACHER_USERNAME, ed_username.text.toString().trim())
                        SPUtils.putString(Field.REM_TEACHER_PASSWORD, ed_password.text.toString().trim())
                        SPUtils.putBoolean(Field.IS_REM, true)
                    } else {
                        SPUtils.putBoolean(Field.IS_REM, false)
                    }
                    break
                }
                if (index == list.size - 1) {
                    //登录失败
                    ToastUtils.showToast(this, "登录失败,请检查用户名或密码")
                }
            }

        }
    }

    override fun loadSuperVisorSuccess(list: List<TeachTable.SupervisionUser>) {
        if (!isEmpty()) {
            for ((index, item) in list.withIndex()) {
                if (item.supervisionNum.equals(ed_username.text.toString().trim()) && item.password.equals(ed_password.text.toString().trim())) {
                    //登陆成功
                    ToastUtils.showToast(this, "登录成功")

                    //跳转同行评价界面
                    supervisorNum = item.supervisionNum
                    val supervisorIntent = Intent(this, SupervisorChooseActivity::class.java)
                    supervisorIntent.putExtra("supervisorNum", supervisorNum)
                    startActivity(supervisorIntent)

                    if (cb_remember.isChecked) {
                        SPUtils.putString(Field.REM_SUPERVISOR_USERNAME, ed_username.text.toString().trim())
                        SPUtils.putString(Field.REM_SUPERVISOR_PASSWORD, ed_password.text.toString().trim())
                        SPUtils.putBoolean(Field.IS_REM, true)
                    } else {
                        SPUtils.putBoolean(Field.IS_REM, false)
                    }
                    break
                }
                if (index == list.size - 1) {
                    //登录失败
                    ToastUtils.showToast(this, "登录失败,请检查用户名或密码")
                }
            }

        }
    }

    override fun loadManagerSuccess(list: List<TeachTable.ManagerUser>) {
        if (!isEmpty()) {
            for ((index, item) in list.withIndex()) {
                if (item.managerNum.equals(ed_username.text.toString().trim()) && item.password.equals(ed_password.text.toString().trim())) {
                    //登陆成功
                    ToastUtils.showToast(this, "登录成功")
                    //跳转同行评价界面
                    managerNum = item.managerNum
                    val managerIntent = Intent(this, ManagerChooseActivity::class.java)
                    managerIntent.putExtra("managerNum", managerNum)
                    startActivity(managerIntent)

                    if (cb_remember.isChecked) {
                        SPUtils.putString(Field.REM_MANAGER_USERNAME, ed_username.text.toString().trim())
                        SPUtils.putString(Field.REM_MANAGER_PASSWORD, ed_password.text.toString().trim())
                        SPUtils.putBoolean(Field.IS_REM, true)
                    } else {
                        SPUtils.putBoolean(Field.IS_REM, false)
                    }
                    break
                }
                if (index == list.size - 1) {
                    //登录失败
                    ToastUtils.showToast(this, "登录失败,请检查用户名或密码")
                }
            }

        }
    }

    override fun loadFailure(msg: String?) {
        ToastUtils.showToast(this, msg)
    }

    fun isEmpty(): Boolean {
        if (TextUtils.isEmpty(ed_username.text.toString().trim())) {
            textlayout_username.isErrorEnabled = true
            textlayout_username.error = "请输入用户名"
            return true
        }
        if (TextUtils.isEmpty(ed_password.text.toString().trim())) {
            textlayout_password.isErrorEnabled = true
            textlayout_password.error = "密码不能为空"
            return true
        }
        return false

    }


    fun showPromiseDialog() {
        var promiseDialog = MaterialDialog.Builder(this@LoginActivity)
                .title("提醒")
                .icon(ContextCompat.getDrawable(this, R.mipmap.ic_student_dialog_tip))
                .content(R.string.dialog_promise_content)
                .negativeText("返回")
                .positiveText("我承诺")
                .positiveColor(ContextCompat.getColor(this, R.color.clo_status_bar))
                .canceledOnTouchOutside(false)
                .onAny({ dialog, which ->
                    if (which == DialogAction.POSITIVE) {
                        showRuleDialog()

                    } else if (which == DialogAction.NEGATIVE) {
                        dialog.dismiss()
                    }
                }).show()

    }

    fun showRuleDialog() {
        MaterialDialog.Builder(this)
                .content("您是否已熟知评教规则和流程！！！")
                .contentColor(ContextCompat.getColor(this, R.color.clo_status_dark_green))
                .negativeText("否")
                .negativeColor(ContextCompat.getColor(this, android.R.color.darker_gray))
                .positiveText("是")
                .positiveColor(ContextCompat.getColor(this, android.R.color.black))
                .canceledOnTouchOutside(false)
                .onAny({ dialog, which ->
                    if (which == DialogAction.POSITIVE) {
                        //跳转
                        val studentEvaIntent = Intent(this, StudentEvaActivity::class.java)
                        studentEvaIntent.putExtra("studentNum", studentNum)
                        startActivity(studentEvaIntent)
                    } else if (which == DialogAction.NEGATIVE) {
                        val evaIntent = Intent(this, EvaluateActivity::class.java)
                        evaIntent.putExtra("studentNum", studentNum)
                        startActivity(evaIntent)

                    }
                }).show()
    }


    class ToolbarHelper {

        companion object {
            fun addMiddleTitle(textView: TextView, toolbar: Toolbar) {
                val params = Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT, Toolbar.LayoutParams.WRAP_CONTENT)
                params.gravity = Gravity.CENTER_HORIZONTAL
                toolbar.addView(textView, params)

            }

        }
    }

}