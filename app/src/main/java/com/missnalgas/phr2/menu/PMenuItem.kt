package com.missnalgas.phr2.menu

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.View

class PMenuItem private constructor(val title: String, val span : Int, val color : Int, val textColor : Int, val image : Drawable?, val clickListener : View.OnClickListener, val hasDescription : Boolean, val description : String) {



    class Builder() {

        private val DEFAULT_TEXT_COLOR by lazy { Color.parseColor("#232324") } /* WHITE GRAYISH */

        var title = "Title"
        var span = 2
        set(value) {
            if(value > 2)
                field = 2

            field = value
        }
        var color = Color.WHITE
        var textColor = DEFAULT_TEXT_COLOR
        var image : Drawable? = null
        var clickListener = View.OnClickListener { /*EMPTY*/ }
        var hasDescription = false
        var description = ""


        fun build() : PMenuItem {
            return PMenuItem(title, span, color, textColor, image, clickListener, hasDescription, description)
        }

    }
}