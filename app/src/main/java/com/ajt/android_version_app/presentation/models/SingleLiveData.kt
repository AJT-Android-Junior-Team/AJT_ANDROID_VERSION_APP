package com.ajt.android_version_app.presentation.models

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveData<T> : MutableLiveData<T>() {
    private val pending = AtomicBoolean()

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        super.observe(owner, { observer_t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(observer_t)
            }
        })
    }

    override fun setValue(value: T) {
        pending.set(true)
        super.setValue(value)
    }
}