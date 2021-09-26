package com.ajt.android_version_app.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.ajt.android_version_app.R
import com.ajt.android_version_app.presentation.models.MainViewModel

class DetailsFragment : Fragment(R.layout.fragment_details) {
    private val fragmentViewModel: MainViewModel by activityViewModels()

    /* View elements */
    private var posterImage : ImageView? = null
    private var androidImage : ImageView? = null
    private var androidName : TextView? = null
    private var releaseDate : TextView? = null
    private var overviewText : TextView? = null
    private var videoButton : Button? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
        setData()
    }

    private fun setData() {
        fragmentViewModel.liveData.value?.let { androidVersion ->
            posterImage?.setImageResource(androidVersion.posterAndroid)
            androidImage?.setImageResource(androidVersion.imageAndroid)
            androidName?.text = androidVersion.versionName
            releaseDate?.text = androidVersion.releaseDate
            overviewText?.text = androidVersion.overviewText
            videoButton?.setOnClickListener {
                openAndroidTrailer(androidVersion.trailerUrl)
            }
        }
    }

    override fun onDestroyView() {
        posterImage = null
        androidImage = null
        androidName = null
        releaseDate = null
        overviewText = null
        videoButton = null

        super.onDestroyView()
    }

    private fun initView(view: View) {
        posterImage = view.findViewById(R.id.background_image)
        androidImage = view.findViewById(R.id.android_image)
        androidName = view.findViewById(R.id.android_name)
        releaseDate = view.findViewById(R.id.release_date)
        overviewText = view.findViewById(R.id.overview_text)
        videoButton = view.findViewById(R.id.button_video)
    }

    private fun openAndroidTrailer(trailerUrl: String) {
        val intentUrl = Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl))
        startActivity(intentUrl)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }
}