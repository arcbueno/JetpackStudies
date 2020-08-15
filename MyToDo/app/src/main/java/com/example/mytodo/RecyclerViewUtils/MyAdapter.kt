package com.example.mytodo.RecyclerViewUtils

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mytodo.Fragments.Main.ListFragmentViewModel
import com.example.mytodo.models.IListaTodo
import com.example.mytodo.models.Todo
import com.example.mytodo.R

class MyAdapter(var viewModel: ListFragmentViewModel, val lista: IListaTodo) :
    RecyclerView.Adapter<MyViewHolder>() {

    private var todos: List<Todo> = ArrayList()
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var todo: Todo = todos[position]
        holder.bind(todo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int = todos.size

    fun setTodo(todos: List<Todo>) {
        this.todos = todos
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, payloads: MutableList<Any>) {
        super.onBindViewHolder(holder, position, payloads)

        holder.text.text = todos[position].Text
        holder.title.text = todos[position].Title
        holder.checked.isChecked = todos[position].Checked

        if (holder.checked.isChecked) {
            holder.layout!!.setBackgroundResource(R.drawable.background_with_border_gray)
        } else {
            holder.layout!!.setBackgroundResource(R.drawable.background_with_border_yellow)
        }

        holder.checked.setOnClickListener {
            todos[position].Checked = !todos[position].Checked
            if (todos[position].Checked) {
                holder.layout!!.setBackgroundResource(R.drawable.background_with_border_gray)
            } else {
                holder.layout!!.setBackgroundResource(R.drawable.background_with_border_yellow)
            }
            lista.update(todos[position])
            notifyDataSetChanged()
        }

        holder.text?.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("todoId", todos[position].Id!!)
            it.findNavController().navigate(R.id.action_fragment_to_fragment_todo_detail, bundle)
        }
    }
}
