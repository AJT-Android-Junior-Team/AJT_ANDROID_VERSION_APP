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
import com.ajt.android_version_app.presentation.models.AndroidVersionViewModelFactory
import com.ajt.android_version_app.presentation.models.DataStorage
import com.ajt.android_version_app.presentation.models.DetailsFragmentViewModel

class DetailsFragment : Fragment() {
    private var binding: FragmentDetailsBinding? = null
    private val args: DetailsFragmentArgs by navArgs()
    private val detailsFragmentViewModel: DetailsFragmentViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            AndroidVersionViewModelFactory(listOf() ,args.androidVersionPosition)
        ).get(DetailsFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setArgsToViewModel()
        setData()
    }

    private fun setArgsToViewModel() {
        detailsFragmentViewModel.setItemPosition(args.androidVersionPosition)
    }

    private fun setData() {
        detailsFragmentViewModel.androidItemPosition.value?.let { position ->
            DataStorage.getVersionByPosition(position).let { androidVersion ->
                binding?.apply {
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
        binding = null
        super.onDestroyView()
    }

    private fun openAndroidTrailer(trailerUrl: String) {
        val intentUrl = Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl))
        startActivity(intentUrl)
    }
}