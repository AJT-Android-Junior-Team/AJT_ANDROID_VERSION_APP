package com.ajt.android_version_app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ajt.android_version_app.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private var androidVersions: AndroidVersions? = null
    private var binding: FragmentDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            androidVersions = bundle.getParcelable(ARGS_VERSION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater)
        initPage()
        return binding?.root
    }

    private fun initPage() {
        androidVersions?.let { androidVersions ->
            binding?.apply {
                backgroundImage.setImageResource(androidVersions.posterAndroid)
                androidImage.setImageResource(androidVersions.imageAndroid)
                androidName.text = androidVersions.versionName
                releaseDate.text = androidVersions.releaseDate
                overviewText.text = androidVersions.overviewText
                buttonVideo.setOnClickListener {
                    openAndroidTrailer(androidVersions.trailerUrl)
                }
            }
        }
    }

    private fun openAndroidTrailer(trailerUrl: String) {
        val intentUrl = Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl))
        startActivity(intentUrl)
    }

    companion object {
        private const val ARGS_VERSION = "ARGS_VERSION"

        @JvmStatic
        fun newInstance(androidVersions: AndroidVersions) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARGS_VERSION, androidVersions)
                }
            }
    }
}