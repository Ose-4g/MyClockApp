package com.ose4g.myclockapp.utlis

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.ose4g.myclockapp.R
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultSharedPreferences
@Inject constructor(@ApplicationContext private val context: Context) : Constants
{
    private lateinit var sharedPreferences:SharedPreferences

    private fun getSharedPrefs():SharedPreferences
    {
        if(!(this::sharedPreferences.isInitialized))
        {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            if(sharedPreferences.all.isEmpty())
            {
                val editor:SharedPreferences.Editor = sharedPreferences.edit()

                //clock values
                editor.putString(
                        context.getString(KEY_CLOCK_STYLE),
                        context.resources.getStringArray(R.array.clock_style_values)[1]
                )

                editor.putBoolean(
                        context.getString(KEY_DISPLAY_TIME_IN_SECONDS),
                        true
                )

                //alarm values
                editor.putString(
                        context.getString(KEY_SILENCE_AFTER),
                        context.resources.getStringArray(R.array.silence_after_values)[2]
                )

                editor.putString(
                        context.getString(KEY_SNOOZE_LENGTH),
                        context.resources.getStringArray(R.array.snooze_length_values)[14]
                )

                editor.putString(
                        context.getString(KEY_VOLUME_BUTTONS),
                        context.resources.getStringArray(R.array.volume_buttons_values)[2]
                )

                editor.putString(
                        context.getString(KEY_START_WEEK_ON),
                        context.resources.getStringArray(R.array.start_week_day_values)[1]
                )

                editor.apply()
            }

        }

        return sharedPreferences
    }

    fun getClockStyle():String?
    = getSharedPrefs().getString(context.getString(KEY_CLOCK_STYLE),BLANK_STRING)

    fun getDisplayTimeInSeconds():Boolean
    = getSharedPrefs().getBoolean(context.getString(KEY_DISPLAY_TIME_IN_SECONDS),true)

    fun getAutomaticHomeClock():Boolean
    = getSharedPrefs().getBoolean(context.getString(KEY_AUTOMATIC_HOME_CLOCK),false)

    fun getHomeTimeZone():String?
    = getSharedPrefs().getString(context.getString(KEY_HOME_TIME_ZONE),BLANK_STRING)
}
