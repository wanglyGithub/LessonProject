package com.wly.baselib.mvvm.base

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.wly.baselib.base.AbsBaseActivity
import java.lang.reflect.ParameterizedType

/**
 * author: wanglyGithub
 * date: 2021/8/12
 * description: MVVM 架构模式
 */
abstract class BaseMvvmActivity<V : ViewDataBinding, VM : BaseViewModel<BaseModel>>:
    AbsBaseActivity() {
    protected var mBinding: V? = null
    protected var mViewModel: VM? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, layoutResId());
        mBinding?.lifecycleOwner = this
        mViewModel = createViewModel()
        initViewObservable()

    }

    //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
    abstract fun initViewObservable()


    open fun createViewModel(): VM {
        return ViewModelProvider(this).get(getVmClazz(this))
    }

    /**
     * 获取当前类绑定的泛型ViewModel-clazz
     */
    @Suppress("UNCHECKED_CAST")
    private fun <VM> getVmClazz(obj: Any): VM {
        return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as VM
    }


    override fun onDestroy() {
        super.onDestroy()
        mBinding?.unbind()
    }


}