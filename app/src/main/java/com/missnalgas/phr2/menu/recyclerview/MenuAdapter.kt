package com.missnalgas.phr2.menu.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.missnalgas.phr2.R
import com.missnalgas.phr2.menu.PMenuItem

class MenuAdapter(private val items : List<PMenuItem>) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    class ViewHolder(itemView: CardView) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view  = LayoutInflater.from(parent.context).inflate(R.layout.menu_viewholder, parent, false) as CardView
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        val view  = holder.itemView as CardView
        view.radius = 25.0f

        val textView = view.findViewById<TextView>(R.id.menu_item_text_view)
        textView.text = item.title

        item.image?.let {
            val imageView = view.findViewById<ImageView>(R.id.menu_item_imageview)
            imageView.setImageDrawable(it)
        }

        item.textColor?.let {
            textView.setTextColor(it)
        }

        item.color?.let {
            view.setCardBackgroundColor(it)
        }

        item.clickListener?.let {
            view.setOnClickListener(it)
        }

        if (position == 0) {
            val linearLayout = view.findViewById<LinearLayout>(R.id.linearlayout)
            LayoutInflater.from(linearLayout.context).inflate(R.layout.textview_layout, linearLayout, true)
        }

    }

    override fun getItemCount(): Int = items.size

}