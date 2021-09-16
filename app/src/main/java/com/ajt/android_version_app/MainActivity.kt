package com.ajt.android_version_app

import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ajt.android_version_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainFragment.MainFragmentListener {
    private var bindingMain: ActivityMainBinding? = null
    private var homeButton = false

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

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            createExitDialog()
        } else {
            if (homeButton == true) {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                homeButton = false
            }
            super.onBackPressed()
        }
    }

    private fun createExitDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage(R.string.exit_message)
            .setPositiveButton(R.string.yes,
                DialogInterface.OnClickListener { dialog, id ->
                    finish()
                    return@OnClickListener
                })
            .setNegativeButton(R.string.no,
                DialogInterface.OnClickListener { dialog, id ->
                    return@OnClickListener
                }
            )
        alertDialog.create().show()
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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        homeButton = true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                homeButton = false
                supportFragmentManager.popBackStack()
                true
            }
            else -> false
        }
    }

}