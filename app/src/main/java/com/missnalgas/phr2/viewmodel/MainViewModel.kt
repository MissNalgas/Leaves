package com.missnalgas.phr2.viewmodel

import android.animation.ArgbEvaluator
import android.app.Application
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.missnalgas.phr2.phrase.Phrase
import kotlin.math.floor

class MainViewModel(app: Application) : AndroidViewModel(app) {

    private val fragments : ArrayList<FragmentStyle> by lazy {
        val array = ArrayList<FragmentStyle>()
        array.add(FragmentStyle(intArrayOf(Color.parseColor("#dd3218"), Color.parseColor("#c7342f"), Color.parseColor("#ed392c"))))
        array.add(FragmentStyle(intArrayOf(Color.parseColor("#372eb8"), Color.parseColor("#163a86"), Color.parseColor("#2a1da6"))))
        array.add(FragmentStyle(intArrayOf(Color.parseColor("#1f1f1f"), Color.parseColor("#262626"), Color.parseColor("#1f1f1f"))))
        return@lazy array
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


    fun fetchData(phr : Phrase) {
        onDataFetch.postValue(phr)
    }

    val backgroundColorLiveData : LiveData<GradientDrawable?> by lazy {
        val argbEvaluator = ArgbEvaluator()
        return@lazy Transformations.map(onPageChangeListener) { offsetsum ->
            offsetsum?.let {
                val position = floor(offsetsum).toInt()
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