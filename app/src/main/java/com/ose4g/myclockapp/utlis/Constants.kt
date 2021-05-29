package com.ose4g.myclockapp.utlis

import android.content.Context
import com.ose4g.myclockapp.R
import javax.inject.Inject

interface Constants 
{
    val BLANK_STRING: String
        get() = ""
    //CLOCK PREFERENCES
    val KEY_CLOCK_STYLE: Int
        get() = R.string.key_clock_style

    val KEY_DISPLAY_TIME_IN_SECONDS: Int
            get() = R.string.key_display_seconds

    val KEY_AUTOMATIC_HOME_CLOCK: Int
        get() = R.string.key_automatic_clock

    val KEY_HOME_TIME_ZONE: Int
        get() = R.string.key_home_time_zone

    val KEY_SET_DAY_TIME: Int
        get() = R.string.key_set_day_time


    //ALARM PREFERENCES
    val KEY_SILENCE_AFTER: Int
        get() = R.string.key_silence_after

    val KEY_SNOOZE_LENGTH: Int
        get() = R.string.key_snooze_length

    val KEY_ALARM_VOLUME: Int
        get() = R.string.key_alarm_volume

    val KEY_VOLUME_BUTTONS: Int
        get() = R.string.key_volume_buttons

    val KEY_START_WEEK_ON: Int
        get() = R.string.key_start_week_on


    //TIMERS
    val KEY_TIMER_RINGTONE : Int
        get() = R.string.key_timer_ringtone


    //Others
    val KEY_LAST_POSITION:Int
        get() = R.string.key_last_position


}