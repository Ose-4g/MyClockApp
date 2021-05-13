package com.ose4g.myclockapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)
object DefaultPreferencesModule
{

    @Provides
    fun providesDefaultPreferences(@ApplicationContext context: Context)
    : SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
}