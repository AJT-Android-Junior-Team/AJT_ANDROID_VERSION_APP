package com.ajt.android_version_app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajt.android_version_app.databinding.FragmentMainBinding
import java.lang.RuntimeException

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding failed")
    private var listener: MainFragmentListener? = null
    private val versions = DataStorage.getVersionsList()
    private val adapter = AndroidAdapter { position ->
        val androids = versions[position]
        listener?.onOpenInfoPage(androids)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainFragmentListener)
            listener = context
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater)
        if (savedInstanceState == null)
            initPage(DataStorage.getVersionsList())
        return binding.root
    }

    private fun initPage(versionsList: List<AndroidVersion>) {
        binding.apply {
            recyclerViewMain.layoutManager = LinearLayoutManager(context)
            recyclerViewMain.adapter = adapter
            addAndroid(versionsList)
        }
    }

    private fun addAndroid(androidVersionsList: List<AndroidVersion>) {
        androidVersionsList.forEach {
            adapter.addAndroid(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface MainFragmentListener {
        fun onOpenInfoPage(androidVersion: AndroidVersion)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}