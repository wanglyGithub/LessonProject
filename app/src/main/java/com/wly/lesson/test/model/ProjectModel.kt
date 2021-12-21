package com.wly.lesson.test.model

import androidx.lifecycle.viewModelScope
import com.wly.baselib.mvvm.base.BaseViewModel
import com.wly.baselib.net.status.StateLiveData
import com.wly.lesson.test.bean.ProjectTree
import com.wly.lesson.test.pro.ProjectRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProjectModel {

//    private val repo = ProjectRepo()
    val projectTreeData = StateLiveData<List<ProjectTree>>()
//
////    fun loadProjectTree() {
////        viewModelScope.launch {
////            repo.loadProjectTree(projectTreeData)
////        }
////    }
//
//
//    fun loadProjectTree2() {
//        viewModelScope.launch {
//            projectTreeData.value = repo.loadProjectTree()
//            repo.loadProjectTree2()
//        }
//    }


}