package com.example.mytodo.Utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.mytodo.Models.Todo
import com.example.mytodo.R
import kotlinx.android.synthetic.main.list_item.view.*

class MyAdapter(private val todos: List<Todo>): RecyclerView.Adapter<MyViewHolder> () {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val todo:Todo = todos[position]
        holder.bind(todo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = todos.size
}

