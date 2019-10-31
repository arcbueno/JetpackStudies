package com.example.mytodo.RecyclerViewUtils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.models.IListaTodo
import com.example.mytodo.models.Todo
import com.example.mytodo.models.TodoViewModel
import com.example.mytodo.R

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

        holder.text?.setOnClickListener{
            val bundle = Bundle()
            bundle.putInt("todoId", todos[position].Id!!)
            it.findNavController().navigate(R.id.action_fragment_to_fragment_todo_detail, bundle)
        }
    }
}
