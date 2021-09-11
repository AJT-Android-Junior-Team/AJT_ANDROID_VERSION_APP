package com.example.openedu

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Android(
    val title: String,
    @DrawableRes val imageAndroid: Int,
    @DrawableRes val posterAndroid: Int,
    val overview_text : String,
    val release_date : String,
    val trailer_url : String
) : Parcelable
