package com.ose4g.myclockapp.utlis

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultSharedPreferences
@Inject constructor(@ApplicationContext private val context: Context) : Constants
{
    private lateinit var sharedPreferences:SharedPreferences

    fun getSharedPrefs():SharedPreferences
    {
        if(!(this::sharedPreferences.isInitialized))
        {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
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
