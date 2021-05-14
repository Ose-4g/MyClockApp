package com.ose4g.myclockapp.activities

import android.content.Intent
import android.media.RingtoneManager
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.ose4g.myclockapp.R
import com.ose4g.myclockapp.utlis.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint

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
        supportActionBar?.elevation = 0f


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return true
    }

    class SettingsFragment : PreferenceFragmentCompat() , Constants{

        lateinit var openSettings: ActivityResultLauncher<Intent>
        lateinit var openRingtones: ActivityResultLauncher<Intent>

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

            openSettings =   registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            }

            openRingtones =   registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            }

        }

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)

            var setTime = findPreference<Preference>(getString(KEY_SET_DAY_TIME))
            setTime?.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                openSettings.launch(Intent(android.provider.Settings.ACTION_DATE_SETTINGS))
                true
            }


            var ringtone = findPreference<Preference>(getString(KEY_TIMER_RINGTONE))
            ringtone?.onPreferenceClickListener = Preference.OnPreferenceClickListener {
                Log.i("clicking in the main", "click click")
                val manager = RingtoneManager(requireActivity())
                manager.setType(RingtoneManager.TYPE_RINGTONE)
                val cursor = manager.cursor
                while(cursor.moveToNext())
                {
                    Log.i("RINGTONE", cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX))
                    Log.i("URI-RINGTONE", cursor.getString(RingtoneManager.URI_COLUMN_INDEX)+"/"+ cursor.getString(RingtoneManager.ID_COLUMN_INDEX))
                }
                true
            }


            var clockStyle = findPreference<ListPreference>(getString(KEY_CLOCK_STYLE))
            clockStyle?.setOnPreferenceChangeListener { preference, newValue ->
                Log.i("clock_style",newValue.toString())
                true
            }
        }

    }
}