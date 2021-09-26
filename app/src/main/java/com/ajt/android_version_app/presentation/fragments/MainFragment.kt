package com.ajt.android_version_app.presentation.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajt.android_version_app.R
import com.ajt.android_version_app.presentation.models.AndroidVersion
import com.ajt.android_version_app.presentation.models.DataStorage
import com.ajt.android_version_app.presentation.adapters.AndroidAdapter
import com.ajt.android_version_app.presentation.models.MainViewModel

class MainFragment : Fragment(R.layout.fragment_main) {
    private val fragmentViewModel : MainViewModel by activityViewModels()
    private var adapter : AndroidAdapter? = null

    /* View elements */
    private var rvList: RecyclerView? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
        initRv(DataStorage.getVersionsList())
    }

    private fun initRv(versionsList: List<AndroidVersion>) {
        adapter = AndroidAdapter(fragmentViewModel)
        rvList?.layoutManager = LinearLayoutManager(context)
        rvList?.adapter = adapter
        addAndroid(versionsList)
    }

    override fun onDestroyView() {
        rvList = null
        adapter = null

        super.onDestroyView()
    }

    private fun initView(view: View) {
        rvList = view.findViewById(R.id.recyclerViewMain)
    }

    private fun addAndroid(androidVersionsList: List<AndroidVersion>) {
        androidVersionsList.forEach {
            adapter?.addAndroid(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}