package com.ajt.android_version_app.presentation.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AndroidVersionViewModelFactory(
    private val androidVersionsList: List<AndroidVersion>,
    private val androidPosition: Int
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MainFragmentViewModel::class.java -> MainFragmentViewModel(androidVersionsList) as T
        DetailsFragmentViewModel::class.java -> DetailsFragmentViewModel(androidPosition) as T
        else -> throw IllegalArgumentException("Unknown ViewModel class")
    }
}