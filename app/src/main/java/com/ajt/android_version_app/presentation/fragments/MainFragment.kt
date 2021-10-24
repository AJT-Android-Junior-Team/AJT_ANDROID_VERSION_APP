package com.ajt.android_version_app.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ajt.android_version_app.R
import com.ajt.android_version_app.presentation.models.AndroidVersion
import com.ajt.android_version_app.presentation.models.DataStorage
import com.ajt.android_version_app.presentation.adapters.AndroidAdapter
import com.ajt.android_version_app.presentation.models.AndroidVersionViewModel
import com.ajt.android_version_app.presentation.models.AndroidVersionViewModelFactory

class MainFragment : Fragment(R.layout.fragment_main) {
    private var mainFragmentViewModel: AndroidVersionViewModel? = null
    private var rvVersionsList: RecyclerView? = null
    private var rvAndroidAdapter: AndroidAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        subscribeToViewModelObservables()
    }

    private fun initView(view: View) {
        mainFragmentViewModel = ViewModelProvider(requireActivity(), AndroidVersionViewModelFactory()).get(AndroidVersionViewModel::class.java)
        rvVersionsList = view.findViewById(R.id.rv_android_version)
        initAndroidVersionList(DataStorage.getVersionsList())
    }

    private fun initAndroidVersionList(versionsList: List<AndroidVersion>) {
        rvAndroidAdapter = AndroidAdapter { androidItem ->
            mainFragmentViewModel?.liveData?.setValue(androidItem)
        }
        rvVersionsList?.adapter = rvAndroidAdapter
        addAndroid(versionsList)
    }

    private fun addAndroid(androidVersionsList: List<AndroidVersion>) {
        androidVersionsList.forEach {
            rvAndroidAdapter?.addAndroid(it)
        }
    }

    private fun subscribeToViewModelObservables() {
        mainFragmentViewModel?.liveData?.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_mainFragment_to_detailsFragment)
        }
    }

    override fun onDestroyView() {
        mainFragmentViewModel = null
        rvVersionsList = null
        rvAndroidAdapter = null

        super.onDestroyView()
    }
}