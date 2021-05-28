package com.ose4g.myclockapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ose4g.myclockapp.fragments.AlarmFragment
import com.ose4g.myclockapp.fragments.ClockFaceFragment
import com.ose4g.myclockapp.fragments.StopwatchFragment
import com.ose4g.myclockapp.fragments.TimerFragment

class ViewPagerAdapter(private val fragmentManager: FragmentManager, val lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle)
{
    val fragments = mutableListOf(AlarmFragment(), ClockFaceFragment(),TimerFragment(),StopwatchFragment())
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }

}