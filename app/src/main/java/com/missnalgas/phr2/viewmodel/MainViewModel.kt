package com.missnalgas.phr2.viewmodel

import android.animation.ArgbEvaluator
import android.app.Application
import android.graphics.drawable.GradientDrawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.missnalgas.phr2.viewpager.Fragments
import kotlin.math.floor

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val onPageChangeListener : MutableLiveData<Float?> by lazy {
        return@lazy MutableLiveData<Float?>()
    }

    private fun gradientCalculator(colorA : Int, colorB : Int, colorC : Int) : GradientDrawable {
        return GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(colorA,colorB, colorC))
    }

    val backgroundColorLiveData : LiveData<GradientDrawable?> by lazy {
        val argbEvaluator = ArgbEvaluator()
        return@lazy Transformations.map(onPageChangeListener) { offsetSum ->
            offsetSum?.let {
                val position = if (floor(offsetSum) > Fragments.FRAGMENTS_COUNT-1) Fragments.FRAGMENTS_COUNT-1 else floor(offsetSum).toInt()
                val offset = offsetSum - position
                val prevColor = Fragments.FRAGMENTS[position].colors

                return@map if (position < 2) {
                    val nextColor = Fragments.FRAGMENTS[position+1].colors
                    gradientCalculator(argbEvaluator.evaluate(offset, prevColor[0], nextColor[0]) as Int, argbEvaluator.evaluate(offset, prevColor[1], nextColor[1]) as Int, argbEvaluator.evaluate(offset, prevColor[2], nextColor[2]) as Int)
                } else {
                    gradientCalculator(prevColor[0], prevColor[1], prevColor[2])
                }

            }
        }
    }

    fun updatePageChangeListener(value : Float) {
        onPageChangeListener.postValue(value)
    }

}