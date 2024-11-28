package com.hal_domae.kadai08_ih.recyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hal_domae.kadai08_ih.R

class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    val itemDate: TextView = itemView.findViewById(R.id.date)
    val itemText: TextView = itemView.findViewById(R.id.text)
}