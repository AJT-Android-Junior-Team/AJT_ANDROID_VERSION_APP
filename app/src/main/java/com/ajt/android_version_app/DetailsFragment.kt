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
    private var androidVersion: AndroidVersion? = null
    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailsBinding failed")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            androidVersion = bundle.getParcelable(ARGS_VERSION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsBinding.inflate(inflater)
        if (savedInstanceState == null) {
            initPage()
        }
        return _binding?.root
    }

    private fun initPage() {
        androidVersion?.let { androidVersions ->
            binding.apply {
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARGS_VERSION = "ARGS_VERSION"

        @JvmStatic
        fun newInstance(androidVersion: AndroidVersion) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARGS_VERSION, androidVersion)
                }
            }
    }
}