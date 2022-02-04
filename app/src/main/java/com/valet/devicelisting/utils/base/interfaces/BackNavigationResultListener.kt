package com.valet.devicelisting.utils.base.interfaces

import com.valet.devicelisting.utils.base.data.BackNavigationResult


interface BackNavigationResultListener {
    fun onNavigationResult(result: BackNavigationResult)
}
