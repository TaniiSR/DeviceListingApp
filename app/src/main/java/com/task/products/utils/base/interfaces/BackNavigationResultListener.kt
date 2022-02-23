package com.task.products.utils.base.interfaces

import com.task.products.utils.base.data.BackNavigationResult


interface BackNavigationResultListener {
    fun onNavigationResult(result: BackNavigationResult)
}
