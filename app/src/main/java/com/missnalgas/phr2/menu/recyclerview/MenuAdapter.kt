package com.missnalgas.phr2.menu.recyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.missnalgas.phr2.R
import com.missnalgas.phr2.menu.PMenuItem

class MenuAdapter(private val items : Array<PMenuItem>) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

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

        val imageView = view.findViewById<ImageView>(R.id.menu_item_imageview)
        if (item.image != null) {
            imageView.visibility = View.VISIBLE
            imageView.setImageDrawable(item.image)
        } else {
            imageView.visibility = View.GONE
        }

        textView.setTextColor(item.textColor)

        view.setCardBackgroundColor(item.color)

        view.setOnClickListener(item.clickListener)

        val description = view.findViewById<TextView>(R.id.description_textview)
        if (item.hasDescription) {
            description.text = item.description
            description.visibility = View.VISIBLE
        } else {
            description.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int = items.size

}