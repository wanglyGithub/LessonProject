package com.wly.lesson.test.pro

import com.wly.baselib.mvvm.rep.BaseRepository
import com.wly.baselib.net.bean.BaseResponse
import com.wly.baselib.utils.UILogUtil
import com.wly.lesson.test.api.NetWorkApi
import com.wly.lesson.test.api.ProjectApi
import com.wly.lesson.test.bean.ProjectTree

/**
 * date: 2021/8/13
 * description:
 */
class ProjectRepo : BaseRepository() {
    private var mService: ProjectApi =
        NetWorkApi.INSTANCE.getApi(ProjectApi::class.java)
    private var mService1: ProjectApi =
        NetWorkApi.INSTANCE.getApi(ProjectApi::class.java)
    suspend fun loadProjectTree(): BaseResponse<List<ProjectTree>> {
        UILogUtil.i("wangly","loadProjectTree instance = ${mService}")

        return executeReq { mService.loadProjectTree() }
    }

    suspend fun loadProjectTree2(): BaseResponse<List<ProjectTree>> {
        UILogUtil.i("wangly","loadProjectTree2 instance = ${mService}")

        return executeReq { mService1.loadProjectTree() }
    }


}