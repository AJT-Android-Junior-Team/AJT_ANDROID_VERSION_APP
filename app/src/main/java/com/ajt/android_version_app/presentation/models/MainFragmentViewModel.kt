package com.ajt.android_version_app.presentation.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainFragmentViewModel(androidVersionsList: List<AndroidVersion>) : ViewModel() {
    private val _androidVersionList = MutableLiveData<List<AndroidVersion>>()
    val androidVersionList: LiveData<List<AndroidVersion>> get() = _androidVersionList

    private val _androidVersionPosition: SingleLiveData<Int> by lazy { SingleLiveData() }
    val androidVersionPosition: LiveData<Int> get() = _androidVersionPosition

    init {
        _androidVersionList.value = androidVersionsList
    }

    fun setAndroidListLiveData(androidVersionsList: List<AndroidVersion>) {
        _androidVersionList.value = androidVersionsList
    }

    fun setAndroidPosition(position: Int) {
        _androidVersionPosition.setValue(position)
    }
}