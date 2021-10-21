package com.ajt.android_version_app.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ajt.android_version_app.R
import com.ajt.android_version_app.presentation.models.AndroidVersion
import com.ajt.android_version_app.presentation.models.DataStorage
import com.ajt.android_version_app.presentation.adapters.AndroidAdapter
import com.ajt.android_version_app.presentation.models.MainViewModel

class MainFragment : Fragment(R.layout.fragment_main) {
    private val fragmentViewModel: MainViewModel by activityViewModels()
    private var adapter: AndroidAdapter? = null

    private var rvVersionsList: RecyclerView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initAndroidVersionList(DataStorage.getVersionsList())
        subscribeToViewModelObservables()
    }

    private fun initView(view: View) {
        rvVersionsList = view.findViewById(R.id.rv_android_version)
    }

    private fun initAndroidVersionList(versionsList: List<AndroidVersion>) {
        adapter = AndroidAdapter { androidItem ->
            fragmentViewModel.liveData.setValue(androidItem)
        }
        rvVersionsList?.adapter = adapter
        addAndroid(versionsList)
    }

    private fun subscribeToViewModelObservables() {
        fragmentViewModel.liveData.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_mainFragment_to_detailsFragment)
        }
    }

    private fun addAndroid(androidVersionsList: List<AndroidVersion>) {
        androidVersionsList.forEach {
            adapter?.addAndroid(it)
        }
    }

    override fun onDestroyView() {
        rvVersionsList = null
        adapter = null

        super.onDestroyView()
    }
}