package com.wly.lesson.test.api

import com.wly.baselib.net.bean.BaseResponse
import com.wly.lesson.test.bean.ProjectTree
import retrofit2.http.GET

/**
 * date: 2021/8/13
 * description:
 */
interface ProjectApi {
    @GET("project/tree/json")
    suspend fun loadProjectTree(): BaseResponse<List<ProjectTree>>


}