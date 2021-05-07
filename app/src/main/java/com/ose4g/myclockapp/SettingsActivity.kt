package com.ose4g.myclockapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.settings, SettingsFragment())
                    .commit()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true
    }

    class SettingsFragment : PreferenceFragmentCompat() {

        lateinit var openSettings: ActivityResultLauncher<Intent>
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            openSettings =   registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            }

        }

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
            var setTime = findPreference<Preference>("set_date_and_time")
            setTime?.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                openSettings.launch(Intent(android.provider.Settings.ACTION_DATE_SETTINGS))
                true
            }

        }

    }
}