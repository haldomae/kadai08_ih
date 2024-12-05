package com.hal_domae.kadai08_ih.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hal_domae.kadai08_ih.R

class ListAdapter(private val data: MutableList<Map<String, String>>): RecyclerView.Adapter<ViewHolder>() {
    // ViewHolderを作成する
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    // 表示するデータの件数を調べる
    override fun getItemCount(): Int {
        return data.size
    }

    // ViewHolderの中身を変更する
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemDate.text = data[position]["date"]
        holder.itemText.text = data[position]["text"]
    }
}