package com.ose4g.myclockapp

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel

@Inject constructor(
    val preferences: SharedPreferences
) : ViewModel()
{

}