package com.ajt.android_version_app.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ajt.android_version_app.R
import com.ajt.android_version_app.presentation.adapters.AndroidAdapter
import com.ajt.android_version_app.presentation.models.DataStorage
import com.ajt.android_version_app.presentation.models.MainFragmentViewModel
import com.ajt.android_version_app.presentation.models.AndroidVersionViewModelFactory

class MainFragment : Fragment(R.layout.fragment_main) {
    private var rvVersionsList: RecyclerView? = null
    private var rvAndroidAdapter: AndroidAdapter? = null
    private val mainFragmentViewModel: MainFragmentViewModel by lazy {
        ViewModelProvider(
            requireActivity(),
            AndroidVersionViewModelFactory(DataStorage.getVersionsList(), 0)
        ).get(MainFragmentViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initParams(view)
        subscribeToViewModelObservables()
    }

    private fun initParams(view: View) {
        rvVersionsList = view.findViewById(R.id.rv_android_version)
        rvAndroidAdapter = AndroidAdapter { androidItemPosition ->
            mainFragmentViewModel.setAndroidPosition(androidItemPosition)
        }
        rvVersionsList?.adapter = rvAndroidAdapter
    }

    private fun subscribeToViewModelObservables() {
        mainFragmentViewModel.apply {
            androidVersionPosition.observe(viewLifecycleOwner) { position ->
                val action = MainFragmentDirections.actionMainFragmentToDetailsFragment(position)
                findNavController().navigate(action)
            }
            androidVersionList.observe(viewLifecycleOwner) { androidVersionList ->
                rvAndroidAdapter?.addAndroidList(androidVersionList)
            }
        }
    }

    override fun onDestroyView() {
        rvVersionsList = null
        rvAndroidAdapter = null

        super.onDestroyView()
    }
}