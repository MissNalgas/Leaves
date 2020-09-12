package com.missnalgas.phr2.viewpager.fragments

import android.os.Bundle
import android.text.Layout.JUSTIFICATION_MODE_INTER_WORD
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.fragment.app.Fragment
import com.missnalgas.phr2.R
import com.missnalgas.phr2.phrase.Phrase

class ContentFragment(@NonNull val data : Phrase) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.content_layout, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.let { view ->
            val tvContent = view.findViewById<TextView>(R.id.phrase_content)
            tvContent.text = data.content
            tvContent.justificationMode = JUSTIFICATION_MODE_INTER_WORD
        }
    }

}