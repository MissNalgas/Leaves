package com.missnalgas.phr2.menu

import android.graphics.drawable.Drawable
import android.view.View

class PMenuItem(val title : String, val span : Int = 1, var color : Int? = null) {
    var image: Drawable? = null
    var textColor: Int? = null

    var clickListener: View.OnClickListener? = null
    private set


    fun setOnClickListener(listener : View.OnClickListener) {
        this.clickListener = listener
    }
}