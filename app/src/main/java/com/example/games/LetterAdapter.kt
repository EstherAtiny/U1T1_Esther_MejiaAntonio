package com.example.games

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button


class LetterAdapter(context: Context?) : BaseAdapter() {
    private val lettersInf: LayoutInflater
    private val letters: Array<String?> = arrayOfNulls(26)
    private val letterInf: LayoutInflater

    init {
        for (i in letters.indices) {
            letters[i] = "" + (i + 'A'.code).toChar()
        }
        lettersInf = LayoutInflater.from(context)
        letterInf = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return letters.size
    }

    override fun getItem(i: Int): Any? {
        return null
    }

    override fun getItemId(i: Int): Long {
        return 0
    }
   override fun getView(i: Int, view: View?, viewGroup: ViewGroup): View {
       val btnLetter: Button
       if (view == null) {
           btnLetter = letterInf.inflate(R.layout.letter, viewGroup, false) as Button
       } else {
           btnLetter = view as Button
       }
       btnLetter.text = letters[i]
       return btnLetter
   }

}
