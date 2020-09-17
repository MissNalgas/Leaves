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
        array.add(FragmentStyle(intArrayOf(Color.parseColor("#b5eec8"), Color.parseColor("#b0e3d2"), Color.parseColor("#91d1b3"))))
        array.add(FragmentStyle(intArrayOf(Color.parseColor("#639373"), Color.parseColor("#608c7e"), Color.parseColor("#5e987d"))))
        array.add(FragmentStyle(intArrayOf(Color.parseColor("#3b6549"), Color.parseColor("#40695c"), Color.parseColor("#2f5744"))))
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