package com.example.mytodo.RecyclerViewUtils

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.Models.IListaTodo
import com.example.mytodo.Models.Todo
import com.example.mytodo.Models.TodoViewModel
import com.example.mytodo.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.list_item.*

class MyAdapter(var viewModel: TodoViewModel,val lista: IListaTodo): RecyclerView.Adapter<MyViewHolder>() {

    private var todos: List<Todo> = ArrayList()
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var todo:Todo = todos[position]
        holder.bind(todo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = todos.size

    fun setTodo(todos: List<Todo>){
        this.todos = todos
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)

        holder.text?.text = todos[position].Text
        holder.title?.text = todos[position].Title
        holder.checked?.isChecked = todos[position].Checked
        holder.checked?.setOnClickListener {
            todos[position].Checked = !todos[position].Checked
            lista.update(todos[position])
            notifyDataSetChanged()
        }
    }
}

