package com.valet.devicelisting.utils.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.valet.devicelisting.utils.base.SingleEvent


fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}

fun <T> LifecycleOwner.observeEvent(
    liveData: LiveData<SingleEvent<T>>,
    action: (t: SingleEvent<T>) -> Unit
) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}