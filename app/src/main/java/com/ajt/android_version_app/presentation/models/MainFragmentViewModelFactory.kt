package com.ajt.android_version_app.presentation.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class MainFragmentViewModelFactory(private val androidVersionsList: List<AndroidVersion>) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainFragmentViewModel::class.java)) {
            return MainFragmentViewModel(androidVersionsList) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}