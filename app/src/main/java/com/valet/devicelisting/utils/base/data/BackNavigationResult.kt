package com.valet.devicelisting.utils.base.data

import android.os.Bundle

data class BackNavigationResult(
    val requestCode: Int,
    val resultCode: Int,
    val data: Bundle? = null
)
