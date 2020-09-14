package com.missnalgas.phr2.menu.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
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
        val view  = holder.itemView as CardView
        view.radius = 25.0f

        val textView = view.findViewById<TextView>(R.id.menu_item_text_view)
        textView.text = items[position].title


        view.setOnClickListener {
            Toast.makeText(view.context, "Hello World", Toast.LENGTH_SHORT).show()
        }

    }

    override fun getItemCount(): Int = items.size

}