package com.example.mytodo.RecyclerViewUtils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.Models.Todo

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

