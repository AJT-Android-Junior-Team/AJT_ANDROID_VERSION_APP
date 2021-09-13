package com.ajt.android_version_app

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajt.android_version_app.databinding.FragmentMainBinding

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
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
        binding = FragmentMainBinding.inflate(inflater)
        initPage(DataStorage.getVersionsList())
        return binding.root
    }

    private fun initPage(versionsList: List<Android>) {
        binding.apply {
            mainRv.layoutManager = LinearLayoutManager(context)
            mainRv.adapter = adapter
            addAndroid(versionsList)
        }
    }

    private fun addAndroid(androidList: List<Android>) {
        androidList.forEach {
            adapter.addAndroid(it)
        }
    }

    interface MainFragmentListener {
        fun onOpenInfoPage(android: Android)
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }
}