package com.ajt.android_version_app.presentation.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val liveData: MutableLiveData<AndroidVersion> by lazy {
        MutableLiveData<AndroidVersion>()
    }
}