package com.missnalgas.phr2.viewpager.fragments

import android.graphics.text.LineBreaker
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.missnalgas.phr2.R
import com.missnalgas.phr2.phrase.Phrase

class ContentFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.content_layout, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.let { view ->
            val tvContent = view.findViewById<TextView>(R.id.phrase_content)
            tvContent.text = Phrase.content
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                tvContent.justificationMode = LineBreaker.JUSTIFICATION_MODE_INTER_WORD
            }

            val tvI = view.findViewById<RelativeLayout>(R.id.view_i)
            tvI.tooltipText = Phrase.author
        }
    }

}