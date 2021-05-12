package com.wly.baselib.livedata
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.wly.baselib.utils.UILogUtil
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @Descriptions: google大神实现的一个复写类 SingleLiveEvent 使用 AtomicBoolean 防止后续前台重新触发时的数据发送
 */
class SingleLiveData<T> : MutableLiveData<T>() {
    companion object {
        const val TAG = "SingleLiveData"
    }

    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        if (hasActiveObservers()) {
            UILogUtil.e(TAG, "多个观察者存在的时候，只会有一个被通知到数据更新")
        }
        super.observe(owner, Observer { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })

    }

    override fun setValue(value: T?) {
        mPending.set(true)
        super.setValue(value)
    }


    @MainThread
    fun call() {
        value = null
    }

}