package com.wly.baselib.refresh

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * author: wanglyGitHub
 * date: 2021-07-03
 * description: 默认为线性Manager
 */

class DefaultLinearLayoutManager : LinearLayoutManager {
    constructor(context: Context?) : super(context)

    constructor(context: Context, orientation: Int, reverseLayout: Boolean) : super(
        context,
        orientation,
        reverseLayout
    )

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        try {
            super.onLayoutChildren(recycler, state)
        } catch (var4: Exception) {
            var4.printStackTrace()
        }

    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler, state: RecyclerView.State): Int {
        return try {
            super.scrollHorizontallyBy(dx, recycler, state)
        } catch (var5: Exception) {
            var5.printStackTrace()
            0
        }

    }

    override fun scrollVerticallyBy(dy: Int, recycler: RecyclerView.Recycler, state: RecyclerView.State): Int {
        return try {
            super.scrollVerticallyBy(dy, recycler, state)
        } catch (var5: Exception) {
            var5.printStackTrace()
            0
        }

    }
}