package com.example.mytodo.Utils

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.Models.Todo
import com.example.mytodo.R

class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(
    R.layout.list_item, parent, false)) {

    private var title: TextView? = null
    private var text: TextView? = null
    private var checked: CheckBox? = null

    init{
        title = itemView.findViewById(R.id.item_title_id)
        text = itemView.findViewById(R.id.item_text_id)
        checked = itemView.findViewById(R.id.item_check_id)
    }

    fun bind(todo: Todo) {
        title?.text = todo.Title
        text?.text = todo.Text
        checked?.isChecked = todo.Checked
    }

}