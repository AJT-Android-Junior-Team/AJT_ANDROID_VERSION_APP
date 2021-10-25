package com.ajt.android_version_app.presentation.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.ajt.android_version_app.databinding.FragmentDetailsBinding
import com.ajt.android_version_app.presentation.models.DataStorage
import com.ajt.android_version_app.presentation.models.DetailsFragmentViewModel
import com.ajt.android_version_app.presentation.models.DetailsFragmentViewModelFactory

class DetailsFragment : Fragment() {
    private lateinit var detailsFragmentViewModel: DetailsFragmentViewModel
    private var detailsBinding: FragmentDetailsBinding? = null
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsBinding = FragmentDetailsBinding.inflate(inflater)
        initParams()
        return detailsBinding?.root
    }

    private fun initParams() {
        detailsFragmentViewModel = ViewModelProvider(
            requireActivity(),
            DetailsFragmentViewModelFactory(args.androidVersionPosition)
        ).get(DetailsFragmentViewModel::class.java)
        detailsFragmentViewModel.setValueToLiveData(args.androidVersionPosition)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() {
        detailsFragmentViewModel.androidItemPosition.value?.let { position ->
            DataStorage.getVersionByPosition(position).let { androidVersion ->
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