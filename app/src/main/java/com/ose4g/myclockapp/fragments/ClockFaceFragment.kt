package com.ose4g.myclockapp.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.ose4g.myclockapp.MainActivityViewModel
import com.ose4g.myclockapp.R
import com.ose4g.myclockapp.databinding.FragmentClockFaceBinding
import dagger.hilt.android.AndroidEntryPoint
import android.text.format.DateFormat
import androidx.core.os.postDelayed
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class ClockFaceFragment : Fragment() {

    lateinit var binding: FragmentClockFaceBinding
    lateinit var viewModel:MainActivityViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_clock_face, container,false)
        val view =  binding.root //inflater.inflate(R.layout.fragment_clock_face, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)

        binding.realAnalogClock
                .setCalendar(Calendar.getInstance())
                .setOpacity(1.0f)
                .setShowSeconds(true).color = Color.WHITE


        val handler = Handler(Looper.getMainLooper())

        val runnable = object: Runnable {
            @SuppressLint("SimpleDateFormat")
            override fun run() {
                val is24hr = DateFormat.is24HourFormat(requireContext())
                Log.i("is24hr", is24hr.toString())
                var time = ""
                when(is24hr)
                {
                    true->
                    {
                        if(binding.amOrPm.visibility != View.GONE)
                        {
                            binding.amOrPm.visibility = View.GONE
                        }
                        val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
                        time = simpleDateFormat.format(Date())
                    }

                    false->
                    {
                        if(binding.amOrPm.visibility != View.VISIBLE)
                        {
                            binding.amOrPm.visibility = View.VISIBLE
                        }
                        val simpleDateFormat = SimpleDateFormat("hh:mm:ss")
                        time = simpleDateFormat.format(Date())
                        val ampm = SimpleDateFormat("a").format(Date())
                        binding.amOrPm.text = ampm
                    }
                }
                binding.digitalTime.text = time
                handler.postDelayed(this,10)
            }
        }

        handler.postDelayed(runnable,1)
        return view

    }

    override fun onResume() {
        super.onResume()
        val clockStyle = viewModel.preferences.getClockStyle()
        if(clockStyle.equals("0"))
        {
            binding.digitalClock.visibility = View.GONE
            binding.realAnalogClock.visibility = View.VISIBLE
        }
        else
        {
            binding.digitalClock.visibility = View.VISIBLE
            binding.realAnalogClock.visibility = View.GONE
        }

    }

}