package com.missnalgas.phr2.menu

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View

class PMenuItem(val title : String, val span : Int = 1, var color : Int = Color.WHITE) {
    var image: Drawable? = null
    var textColor: Int = Color.parseColor("#232324")

    var clickListener: View.OnClickListener =
        View.OnClickListener { /*EMPTY*/}
    private set

    var hasDescription = false
    var description = ""


    fun setOnClickListener(listener : View.OnClickListener) {
        this.clickListener = listener
    }
}