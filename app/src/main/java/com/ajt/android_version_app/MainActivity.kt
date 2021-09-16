package com.ajt.android_version_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ajt.android_version_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainFragment.MainFragmentListener {
    private var bindingMain: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain?.root)
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.frameLayoutMain, MainFragment.newInstance())
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingMain = null
    }

    override fun onOpenInfoPage(androidVersion: AndroidVersion) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.frameLayoutMain, DetailsFragment.newInstance(androidVersion))
            .commit()
    }

}