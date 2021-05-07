package com.ose4g.myclockapp.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ose4g.myclockapp.fragments.ClockFaceFragment

class ViewPagerAdapter(val fragmentManager: FragmentManager, val lifecycle: Lifecycle)
    : FragmentStateAdapter(fragmentManager, lifecycle)
{
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        return ClockFaceFragment()
    }

}