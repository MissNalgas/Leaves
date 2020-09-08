package com.missnalgas.phr2.viewmodel

import android.animation.ArgbEvaluator
import android.app.Application
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import kotlin.math.floor

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val onPageChangeListener : MutableLiveData<Float?> by lazy {
        return@lazy MutableLiveData<Float?>()
    }

    private fun gradientCalculator(colorA : Int, colorB : Int, colorC : Int) : GradientDrawable {
        return GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(colorA,colorB, colorC))
    }

    val backgroundColorLiveData : LiveData<GradientDrawable?> by lazy {
        val argbEvaluator = ArgbEvaluator()
        return@lazy Transformations.map(onPageChangeListener) { offsetsum ->
            offsetsum?.let {
                val position = floor(offsetsum).toInt()
                val offset = offsetsum - position
                val prevColor = intArrayOf(Color.parseColor("#dd3218"), Color.parseColor("#c7342f"), Color.parseColor("#ed392c"))
                val nextColor = intArrayOf(Color.parseColor("#264998"), Color.parseColor("#2b4f9f"), Color.parseColor("#425d95"))


                if (position == 1 ) {
                    gradientCalculator(nextColor[0], nextColor[1], nextColor[2])
                } else {
                    val background = gradientCalculator(argbEvaluator.evaluate(offset, prevColor[0], nextColor[0]) as Int, argbEvaluator.evaluate(offset, prevColor[1], nextColor[1]) as Int, argbEvaluator.evaluate(offset, prevColor[2], nextColor[2]) as Int)
                    return@let background as GradientDrawable
                }

            }
        }
    }

    fun updatePageChangeListener(value : Float) {
        onPageChangeListener.postValue(value)
    }

}