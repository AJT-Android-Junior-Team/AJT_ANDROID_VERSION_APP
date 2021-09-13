package com.ajt.android_version_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ajt.android_version_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainFragment.MainFragmentListener {
    private lateinit var bindingMain: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain.root)
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.main_fl, MainFragment.newInstance())
            .commit()
    }

    override fun onOpenInfoPage(android: Android) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .add(R.id.main_fl, DetailsFragment.newInstance(android))
            .commit()
    }

}