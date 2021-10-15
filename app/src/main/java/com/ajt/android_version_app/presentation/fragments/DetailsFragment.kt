package com.ajt.android_version_app.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.ajt.android_version_app.databinding.FragmentDetailsBinding
import com.ajt.android_version_app.presentation.models.MainViewModel

class DetailsFragment : Fragment() {
    private val fragmentViewModel: MainViewModel by activityViewModels()
    private var detailsBinding: FragmentDetailsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsBinding = FragmentDetailsBinding.inflate(inflater)
        return detailsBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() {
        fragmentViewModel.liveData.value?.let { androidVersion ->
            detailsBinding?.apply {
                backgroundImage.setImageResource(androidVersion.posterAndroid)
                androidImage.setImageResource(androidVersion.imageAndroid)
                androidName.text = androidVersion.versionName
                releaseDate.text = androidVersion.releaseDate
                overviewText.text = androidVersion.overviewText
                buttonVideo.setOnClickListener {
                    openAndroidTrailer(androidVersion.trailerUrl)
                }
            }
        }
    }

    override fun onDestroyView() {
        detailsBinding = null
        super.onDestroyView()
    }

    private fun openAndroidTrailer(trailerUrl: String) {
        val intentUrl = Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl))
        startActivity(intentUrl)
    }
}