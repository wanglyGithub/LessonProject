package com.wly.lesson

import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.wly.baselib.base.KTBaseActivity
import com.wly.baselib.utils.UILogUtil
import com.wly.lesson.contract.TestConstract
import com.wly.lesson.dsl.customTextWatch
import com.wly.lesson.mvp.KTMainPresenter
import com.wly.lesson.test.model.ProjectModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class MainActivity : KTBaseActivity<KTMainPresenter, TestConstract.View>(), TestConstract.View {
    override fun initPresenter(): KTMainPresenter = KTMainPresenter()
    private val mViewModel = ProjectModel()

    override fun layoutResId(): Int = R.layout.activity_main

    override fun initView() {
        super.initView()

        Glide.with(this)
            .load("https://saas-oss.mumway.com/auntsass/image/202108/61b7681aa759394d636042974d654363.png")
            .override(1090,500)

            .into(image);




        mPresenter?.request(map = HashMap())

        //dsl
        edit.addTextChangedListener(customTextWatch {
            afterTextChanged {

            }
        })

//        mViewModel.projectTreeData.observe(this, { data ->
//
//            Log.i("wangly", "data = ${data.data}")
//
//        })


        mViewModel.projectTreeData.observeState(this,callback = {

            onSuccess {
                UILogUtil.d("wangly","$it")
            }

            onFail { i, s ->

                UILogUtil.e("wangly","$i")

            }

            onComplete {
                UILogUtil.e("wangly","onComplete")

            }

        })
    }

    override fun showData(data: String) {
        UILogUtil.d(message = "result: $data")
    }

    override fun showEmpty() {
        UILogUtil.d(message = "showEmpty: ")
    }


    fun onReqData(view: View) {
//        mViewModel.loadProjectTree2()
    }


    suspend fun getUser(){
        withContext(Dispatchers.IO){
            delay(90)
        }

        return
    }
}
