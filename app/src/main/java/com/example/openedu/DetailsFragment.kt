package com.example.openedu

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.openedu.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {
    private var android: Android? = null
    private lateinit var detailsBinding : FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            android = it.getParcelable(ARGS_VERSION)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        detailsBinding = FragmentDetailsBinding.inflate(inflater)
        initPage()
        return detailsBinding.root
    }

    private fun initPage() {
        if (android != null) {
            detailsBinding.apply {
                backgroundImage.setImageResource(android!!.posterAndroid)
                androidImage.setImageResource(android!!.imageAndroid)
                androidName.text = android!!.title
                releaseDate.text = android!!.release_date
                overviewText.text = android!!.overview_text
                buttonVideo.setOnClickListener {
                    openAndroidTrailer(android!!.trailer_url)
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
        fun newInstance(android: Android) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARGS_VERSION, android)
                }
            }
    }
}