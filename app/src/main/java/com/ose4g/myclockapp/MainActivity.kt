package com.ose4g.myclockapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.ose4g.myclockapp.adapters.ViewPagerAdapter
import com.ose4g.myclockapp.databinding.ActivityMainBinding

lateinit var binding:ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //use data binding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        //set up the toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //set up the viewpager2 and the tabLayout
        binding.viewpager2.let {
            it.adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        }

        val icons = listOf<Int>( R.drawable.alarm_icon, R.drawable.clock_icon, R.drawable.timer_icon, R.drawable.stopwatch_icon)

        TabLayoutMediator(binding.tablayout, binding.viewpager2, TabLayoutMediator.TabConfigurationStrategy{
            tab, position ->
            tab.icon = getDrawable(icons[position])
        }).attach()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        startActivity(Intent(this@MainActivity,SettingsActivity::class.java))
        return true
    }
}