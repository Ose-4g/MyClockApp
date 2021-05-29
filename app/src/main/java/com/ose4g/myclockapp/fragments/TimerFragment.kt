package com.ose4g.myclockapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.DataBinderMapperImpl
import androidx.lifecycle.lifecycleScope
import com.ose4g.myclockapp.R
import com.ose4g.myclockapp.databinding.FragmentTimerBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class TimerFragment : Fragment() {

    lateinit var binding: FragmentTimerBinding
    lateinit var timeInput: MutableStateFlow<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_timer,container,false)

        timeInput = MutableStateFlow("")
        var _timeInput = ""

        val keys = mutableListOf(
           binding.zeroKey,
            binding.oneKey,
            binding.twoKey,
            binding.threeKey,
            binding.fourKey,
            binding.fiveKey,
            binding.sixKey,
            binding.sevenKey,
            binding.eightKey,
            binding.nineKey
        )


        for(i in keys)
        {
            i.setOnClickListener {
                _timeInput += i.text
                _timeInput = if (_timeInput.length<=6) _timeInput else _timeInput.substring(0,6)
                _timeInput = (_timeInput.toInt()).toString()
                lifecycleScope.launch {
                    timeInput.emit(_timeInput)
                }
            }
        }

        binding.delete.setOnClickListener {
            val n = _timeInput.length
            if(n > 0)
            {
                _timeInput = _timeInput.substring(0,n-1)
                lifecycleScope.launch {
                    timeInput.emit(_timeInput)
                }
            }

        }


        lifecycleScope.launch {
            timeInput.collect {
                var text = it

                if(it.length<6)
                {

                    val n = 6-text.length
                    for (i in 1..n)
                    {
                        text = "0$text"
                    }
                }
                else
                {
                    text = text.substring(0,6);
                }

                binding.hourVariable = text.substring(0,2)
                binding.minuteVariable = text.substring(2,4)
                binding.secondVariable = text.substring(4,6)
            }
        }

        lifecycleScope.launch {

        }


        return binding.root
    }


}