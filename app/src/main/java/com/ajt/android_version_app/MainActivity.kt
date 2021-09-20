package com.ajt.android_version_app

import android.content.DialogInterface
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ajt.android_version_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainFragment.MainFragmentListener {
    private var bindingMain: ActivityMainBinding? = null

    companion object {
        private const val EMPTY_FRAGMENT = -1
        private const val LIST_FRAGMENT = 0
        private const val DETAILS_FRAGMENT = 1

        private var fragmentStatus = EMPTY_FRAGMENT
        private var homeButton = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingMain = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingMain?.root)
        if (savedInstanceState == null)
            onOpenMainPage()
        when (homeButton) {
            true -> supportActionBar?.setDisplayHomeAsUpEnabled(true)
            false -> supportActionBar?.setDisplayHomeAsUpEnabled(false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        bindingMain = null
    }

    private fun onOpenMainPage() {
        fragmentStatus = LIST_FRAGMENT
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayoutMain, MainFragment.newInstance())
            .commit()
    }

    override fun onOpenInfoPage(androidVersion: AndroidVersion) {
        fragmentStatus = DETAILS_FRAGMENT
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayoutMain, DetailsFragment.newInstance(androidVersion))
            .commit()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        homeButton = true
    }

    private fun createExitDialog() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setMessage(R.string.exit_message)
            .setPositiveButton(
                R.string.yes,
                DialogInterface.OnClickListener { _, _ ->
                    finish()
                    return@OnClickListener
                })
            .setNegativeButton(
                R.string.no,
                DialogInterface.OnClickListener { _, _ ->
                    return@OnClickListener
                }
            )
        alertDialog.create().show()
    }

    override fun onBackPressed() {
        when (fragmentStatus) {
            LIST_FRAGMENT -> createExitDialog()
            DETAILS_FRAGMENT -> {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
                homeButton = false
                onOpenMainPage()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> false
    }

}