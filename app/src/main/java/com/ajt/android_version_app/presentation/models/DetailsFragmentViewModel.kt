package com.ajt.android_version_app.presentation.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class DetailsFragmentViewModel(idAndroidItem: Int) : ViewModel() {
    private val _androidItemPosition: SingleLiveData<Int> by lazy { SingleLiveData() }
    val androidItemPosition: LiveData<Int> get() = _androidItemPosition

    init {
        _androidItemPosition.setValue(idAndroidItem)
    }

    fun setValueToLiveData(idAndroidItem: Int) = _androidItemPosition.setValue(idAndroidItem)
}