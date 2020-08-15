package com.example.mytodo.RecyclerViewUtils

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.GridLayout
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.models.Todo
import com.example.mytodo.R

class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(
    inflater.inflate(
        R.layout.list_item, parent, false
    )
) {

    var title: TextView
    var text: TextView
    var checked: CheckBox
    var layout: GridLayout?

    init {
        title = itemView.findViewById(R.id.item_title_id)
        text = itemView.findViewById(R.id.item_text_id)
        checked = itemView.findViewById(R.id.item_check_id)
        layout = itemView.findViewById(R.id.list_item_layout)
        
    }

    fun bind(todo: Todo) {
        title.text = todo.Title
        text.text = todo.Text
        checked.isChecked = todo.Checked

    }
}