package com.ose4g.myclockapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.ose4g.myclockapp.MainActivityViewModel
import com.ose4g.myclockapp.R
import com.ose4g.myclockapp.adapters.ViewPagerAdapter
import com.ose4g.myclockapp.databinding.ActivityMainBinding
import com.ose4g.myclockapp.utlis.Constants
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject




@AndroidEntryPoint
class MainActivity : AppCompatActivity() ,Constants{

    lateinit var viewModel: MainActivityViewModel
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //use data binding
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        Log.i("seenin",viewModel.preferences.getClockStyle()!!)

        //causes shared preferences to be initialized with default values.
        viewModel.preferences.getClockStyle();


        //binding of the current view
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        //set up the toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        //set up the viewpager2 and the tabLayout
        binding.viewpager2.let {
            it.adapter = ViewPagerAdapter(supportFragmentManager,lifecycle)
        }

        val icons = listOf<Int>(R.drawable.alarm_icon, R.drawable.clock_icon, R.drawable.timer_icon, R.drawable.stopwatch_icon)

        TabLayoutMediator(binding.tablayout, binding.viewpager2, TabLayoutMediator.TabConfigurationStrategy{
            tab, position ->
            tab.icon = ContextCompat.getDrawable(this,icons[position])
        }).attach()

        val position = viewModel.preferences.getLastPosition()
        binding.viewpager2.setCurrentItem(position,true)
    }


    //creating the options menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    //setting the options menu function
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.action_intelligent_voice ->{}
            R.id.action_settings ->{
                startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
                return true
            }
        }
        return true
    }

    override fun onStop() {
        viewModel.preferences.setLastPosition(binding.viewpager2.currentItem)
        super.onStop()
    }
}
