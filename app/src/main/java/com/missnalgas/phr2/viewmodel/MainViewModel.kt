package com.missnalgas.phr2.viewmodel

import android.animation.ArgbEvaluator
import android.app.Application
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.missnalgas.phr2.phrase.Phrase
import kotlin.math.floor

class MainViewModel(app: Application) : AndroidViewModel(app) {

    companion object {
        const val FRAGMENTS_COUNT = 3
    }

    private val fragments : List<FragmentStyle> by lazy {
        return@lazy List(FRAGMENTS_COUNT){
            when(it) {
                0 -> FragmentStyle(intArrayOf(Color.parseColor("#FF8A65"), Color.parseColor("#FF7043"), Color.parseColor("#FF5722")))
                1 -> FragmentStyle(intArrayOf(Color.parseColor("#009688"), Color.parseColor("#00897B"), Color.parseColor("#00796B")))
                2 -> FragmentStyle(intArrayOf(Color.parseColor("#00796B"), Color.parseColor("#00695C"), Color.parseColor("#004D40")))
                else -> FragmentStyle()
            }
        }
    }

    private val onPageChangeListener : MutableLiveData<Float?> by lazy {
        return@lazy MutableLiveData<Float?>()
    }

    private fun gradientCalculator(colorA : Int, colorB : Int, colorC : Int) : GradientDrawable {
        return GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(colorA,colorB, colorC))
    }

    private val onDataFetch : MutableLiveData<Phrase?> by lazy {
        return@lazy MutableLiveData<Phrase?>()
    }

    val dataLiveData : LiveData<Phrase?> by lazy {
        return@lazy Transformations.map(onDataFetch) { phr ->
            phr
        }
    }



    val backgroundColorLiveData : LiveData<GradientDrawable?> by lazy {
        val argbEvaluator = ArgbEvaluator()
        return@lazy Transformations.map(onPageChangeListener) { offsetsum ->
            offsetsum?.let {
                val position = if (floor(offsetsum) > FRAGMENTS_COUNT-1) FRAGMENTS_COUNT-1 else floor(offsetsum).toInt()
                val offset = offsetsum - position
                val prevColor = fragments[position].colors

                return@map if (position < 2) {
                    val nextColor = fragments[position+1].colors
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