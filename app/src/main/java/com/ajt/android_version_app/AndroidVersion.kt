package com.ajt.android_version_app

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class AndroidVersion(
    val versionName: String,
    @DrawableRes val imageAndroid: Int,
    @DrawableRes val posterAndroid: Int,
    val overviewText: String,
    val releaseDate: String,
    val trailerUrl: String,
) : Parcelable
