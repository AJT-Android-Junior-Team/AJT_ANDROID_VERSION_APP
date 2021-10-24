package com.ajt.android_version_app.presentation.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class AndroidVersionViewModelFactory() : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AndroidVersionViewModel::class.java)) {
            return AndroidVersionViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}