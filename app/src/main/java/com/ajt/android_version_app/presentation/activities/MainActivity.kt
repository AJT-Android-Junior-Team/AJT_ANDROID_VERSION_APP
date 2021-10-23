package com.ajt.android_version_app.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.ajt.android_version_app.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationUI.setupActionBarWithNavController(this, this.findNavController(R.id.my_nav_host_fragment))

        initMainViewModel()
    }

    private fun initMainViewModel() {
        application
    }

    override fun onSupportNavigateUp(): Boolean {
        return this.findNavController(R.id.my_nav_host_fragment).navigateUp()
    }

}