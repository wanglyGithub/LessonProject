package com.wly.baselib.base.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.IntRange
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener
import com.scwang.smart.refresh.layout.listener.OnRefreshListener
import com.wly.baselib.R
import com.wly.baselib.refresh.DefaultLinearLayoutManager

/**
 * author: wangliyun
 * date: 2021-07-01
 * description: 列表刷新统一封装
 */
abstract class BaseListFragment<B : BaseQuickAdapter<T, BaseViewHolder>, T> : BaseFragment() {

    companion object{
        val TAG = "Refresh组件"
    }
    private var pager = 1
    private var isFirsReq = true
    private var isAutoLoadMore = false


    private var mRecyclerView: RecyclerView? = null
    private var mAdapter: B? = null

    private var mRefreshLayout: SmartRefreshLayout? = null





    override fun getLayoutId(): Int = R.layout.layout_refresh_loadmore_default

    fun getCoverStatusLayoutResId():Int = R.id.default_refreshLayout

    open fun getRecyclerView() = mRecyclerView


    open fun getSmartRefreshLayout() = mRefreshLayout



    override fun initView(root: View?, savedInstanceState: Bundle?) {
        super.initView(root, savedInstanceState)

        var recyclerView = getRecyclerView()
        if (null == recyclerView){
            recyclerView = root?.findViewById(R.id.default_recyclerView)
        }

        var refreshLayout = getSmartRefreshLayout()

        if (null == refreshLayout){

            refreshLayout = root?.findViewById(R.id.default_refreshLayout)
        }

        if (recyclerView != null && refreshLayout != null) {
            bindView(recyclerView, refreshLayout)
        }


    }


    protected open fun startPager(): Int = 1

    fun getPage(): Int = pager

    override fun initData() {

    }




    fun getAdapter(): B? = mAdapter


    abstract fun initRecyclerAdapter(): B


    fun isUsedRefreshAndLoadMore() = mRefreshLayout != null

    fun autoRefresh() {
        getSmartRefreshLayout()?.autoRefresh()
    }
    /**
     * 不满一屏时，是否加载更多
     * 为true时，不自动加载。为false自动加载
     *
     * @return
     */
    fun disableLoadMoreIfNotFullPage(): Boolean = true


    open fun getLayoutManager(): RecyclerView.LayoutManager {
        return DefaultLinearLayoutManager(context)
    }


    private fun bindView(recyclerView: RecyclerView, refreshLayout: SmartRefreshLayout) {
        this.mRecyclerView = recyclerView
        this.mRefreshLayout = refreshLayout
        this.pager = startPager()

        this.mAdapter = initRecyclerAdapter()

        recyclerView.layoutManager = getLayoutManager()
        mAdapter?.onAttachedToRecyclerView(recyclerView)

        recyclerView.adapter = mAdapter

        setRefreshAndLoadMore()
    }


    fun setAdapterData(list: MutableList<T>) {
        if (isFirstPager()) {
            mAdapter?.setList(list)
            setNewData(list)
        } else {
            addMoreData(list)
        }

    }

    /**
     * 重新加载数据
     *
     * @param list
     */
    open  fun setNewData(list: MutableList<T>) {
        mAdapter?.setList(list)
    }

    /**
     * 在list后，再增加新的数据
     *
     * @param list
     */
    open  fun addMoreData(list: MutableList<T>) {
        mAdapter?.addData(list)
    }

    open fun addData(list: MutableList<T>) {
        mAdapter?.addData(list)
    }

    open  fun addData(@IntRange(from = 0) position: Int, @NonNull list: MutableList<T>) {
        mAdapter?.addData(position, list)
    }

    open  fun addData(@IntRange(from = 0) position: Int, @NonNull data: T) {
        mAdapter?.addData(position, data)
    }

    open fun addData(@NonNull data: T) {
        mAdapter?.addData(data)
    }

    private fun setRefreshAndLoadMore() {
        if (!isUsedRefreshAndLoadMore()) {
            return
        }
        if (isUsedRefreshAndLoadMore()){
            mAdapter?.loadMoreModule?.isAutoLoadMore = true
            mAdapter?.loadMoreModule?.isEnableLoadMoreIfNotFullPage = true
        } else {
            setEnableLoadMore(true)
            mRefreshLayout?.setEnableScrollContentWhenLoaded(true)
            mRefreshLayout?.setEnableLoadMoreWhenContentNotFull(disableLoadMoreIfNotFullPage())
        }
        setEnableRefresh(true)
        setRefreshListener()
        setLoadMoreListener()


    }

    private fun setRefreshListener() {
        mRefreshLayout?.setOnRefreshListener (object : OnRefreshListener{
            override fun onRefresh(refreshLayout: RefreshLayout) {
                pager = startPager()
                Log.i(TAG,"setRefreshListener onRefresh 开始刷新....")
                doRefresh()
            }
        })
    }
    private fun setLoadMoreListener() {
        if (!enableUsedLoadMore()) {
            Log.i(TAG,"setLoadMoreListener 上拉加载 状态= 禁止 = ${!enableUsedLoadMore()}")
            return
        }

        // 使用Adapter 中的上拉加载
        if (enabledUsedAdapterLoadMore()){
            Log.i(TAG,"setLoadMoreListener 上拉加载  使用Adapter 中的上拉加载")

            mAdapter?.loadMoreModule?.setOnLoadMoreListener(object : com.chad.library.adapter.base.listener.OnLoadMoreListener{
                override fun onLoadMore() {
                    Log.i(TAG,"setLoadMoreListener 上拉加载  onLoadMore 开始加载上拉加载")
                    doLoadMore()
                }
            })
            return
        }

        // 使用 SmartRefreshLayout 上拉加载
        mRefreshLayout?.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                doLoadMore()

            }
        })
    }


    protected open fun enableUsedRefresh(): Boolean = true
    protected open fun enableUsedLoadMore(): Boolean = true
    protected open fun enabledUsedAdapterLoadMore():Boolean = true



    fun setEnableRefresh(enable: Boolean) {
        mRefreshLayout?.setEnableRefresh(enableUsedRefresh() && enable)
    }


    fun setEnableLoadMore(enable: Boolean) {
        if (enabledUsedAdapterLoadMore()){
            mAdapter?.loadMoreModule?.isEnableLoadMore = enable
            return
        }
        mRefreshLayout?.setEnableLoadMore(enable)
        mRefreshLayout?.setEnableLoadMore(enableUsedLoadMore() && enable)
    }

    fun enabledRefreshOrLoadMore(enableRefresh: Boolean = enableUsedRefresh(),enableLoadMore:Boolean) {
        setEnableRefresh(enableRefresh)
        setEnableLoadMore(enableLoadMore)
    }


    protected fun isFirstPager(): Boolean {
        return getPage() == startPager()
    }


    ///------请求部分-----


    protected fun doRefresh() {
        getHttpReq(pager = startPager())
        setEnableLoadMore(false)
    }

    protected fun doLoadMore() {
        getHttpReq(if (isFirsReq) pager else ++pager)
        setEnableRefresh(false)
    }
    protected fun doHttpReq(){
        getHttpReq(this.pager)
        setEnableLoadMore(false)
        setEnableRefresh(false)
    }

    /**
     * 发起Http 请求
     * @param page 当前页面
     */
    protected open fun doHttpReq(page: Int) {

    }

    /**
     * @param isRefresh 判断是下拉刷新还是加载更多
     */

    protected fun doHttpReq(isRefresh: Boolean) {

    }

    protected fun isRefresh(): Boolean = isFirstPager() && !isAutoLoadMore

    private fun getHttpReq(pager: Int = 0) {
        isFirsReq = false
        doHttpReq(pager)
        doHttpReq(isRefresh())

    }


    /**
     * 加载完成之后
     */

    fun loadComplete() {
        if (isRefresh()) {
            refreshComplete()
        } else {
            loadMoreComplete()
        }
        enabledRefreshOrLoadMore(enableUsedRefresh(),enableUsedLoadMore())
    }

    fun loadComplete(hasMoreData: Boolean) {
        if (isRefresh()) {
            refreshComplete()
        } else {
            loadMoreComplete()
        }

        if (hasMoreData) {
            enabledRefreshOrLoadMore(enableUsedRefresh(),enableUsedLoadMore())
        } else {
            setEnableRefresh(enableUsedRefresh())
            this.loadMoreEnd()
        }
    }


    fun refreshComplete() {
        mRefreshLayout?.finishRefresh(true)
    }


    //-------上拉加载Start-----------
    fun loadMoreComplete() {
        if (enabledUsedAdapterLoadMore()){
            mAdapter?.loadMoreModule?.loadMoreComplete()
            return
        }
        mRefreshLayout?.finishLoadMore()
    }


    fun loadMoreFail(emptyMsg:String  = ""){
        if (enabledUsedAdapterLoadMore()){
            Log.i(TAG,"上拉加载 loadMoreFail # 使用Adapter 加载样式")
            mAdapter?.loadMoreModule?.loadMoreFail()
            if (isFirstPager()){
//                showEmptyView()
                //TODO: 显示空界面
            } else  {
                showFailViewFromType(emptyMsg)
            }
            return
        }
        mRefreshLayout?.finishLoadMore()
    }

    private fun showFailViewFromType(failMsg:String?){
        if (isFirstPager()){
            failMsg?.let {
                //TODO：显示失败页面
//                showFailView(failMsg)
                return
            }

            //TODO: 显示失败页面
//            showEmptyView(failMsg)
        } else {
            mAdapter?.loadMoreModule?.loadMoreFail()
        }
    }

    fun loadMoreEnd() {
        if (enabledUsedAdapterLoadMore()){
            Log.i(TAG,"上拉加载 loadMoreEnd # 使用Adapter 加载样式")

            mAdapter?.loadMoreModule?.loadMoreEnd()
            return
        }
        mRefreshLayout?.finishLoadMoreWithNoMoreData()
    }

    fun loadMoreNotData(noMoreData:Boolean){
        if (enabledUsedAdapterLoadMore()){
            Log.i(TAG,"上拉加载 loadMoreNotData # 使用Adapter 加载样式")
            mAdapter?.loadMoreModule?.loadMoreEnd()
            return
        }
        if (noMoreData){
          mRefreshLayout?.finishLoadMore(800,true,false)
        }  else {
            mRefreshLayout?.setNoMoreData(false)
            mRefreshLayout?.setEnableLoadMore(true)
        }

    }


    //-------上拉加载End-----------





}