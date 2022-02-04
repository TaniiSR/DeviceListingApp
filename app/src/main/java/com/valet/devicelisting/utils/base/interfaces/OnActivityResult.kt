package com.valet.devicelisting.utils.base.interfaces

interface OnActivityResult<Result> {
    /**
     * Called after receiving a result from the target activity
     */
    fun onActivityResult(result: Result);
}
