package com.ose4g.myclockapp.utlis

import android.content.Context
import android.util.AttributeSet
import com.turki.vectoranalogclockview.VectorAnalogClock

class MyAnalogClock : VectorAnalogClock
{

    fun init() {
        initializeSimple()
    }

    constructor(context:Context):super(context)
    {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet)
            :super(context,attributeSet){
        init()
    }


    constructor(context: Context,attributeSet: AttributeSet,defStyle:Int)
    :super(context,attributeSet,defStyle)
    {
        init()

    }
}