package com.example.mytodo.RecyclerViewUtils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.Models.Todo
import com.example.mytodo.R
import java.text.FieldPosition

class MyViewHolder(inflater: LayoutInflater, parent: ViewGroup) : RecyclerView.ViewHolder(inflater.inflate(
    R.layout.list_item, parent, false)) {

    var title: TextView? = null
    var text: TextView? = null
    var checked: CheckBox? = null

    var onItemClick: ((Todo) -> Unit)? = null

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