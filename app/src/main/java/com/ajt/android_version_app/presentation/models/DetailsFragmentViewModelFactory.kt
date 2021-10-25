package com.ajt.android_version_app.presentation.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class DetailsFragmentViewModelFactory(private val idAndroidItem: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsFragmentViewModel::class.java)) {
            return DetailsFragmentViewModel(idAndroidItem = idAndroidItem) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}