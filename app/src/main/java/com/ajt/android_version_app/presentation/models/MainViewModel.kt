package com.ajt.android_version_app.presentation.models

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val liveData: SingleLiveData<AndroidVersion> by lazy {
        SingleLiveData()
    }
}