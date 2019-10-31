package com.example.mytodo.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.mytodo.Db.MyApplication
import com.example.mytodo.Models.Todo
import com.example.mytodo.Models.TodoViewModel

import com.example.mytodo.R
import kotlinx.android.synthetic.main.fragment_add_todo.*


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddTodoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_todo, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_button.setOnClickListener{
            it.hideKeyboard()
            var todo = Todo(null,new_todo_title.text.toString(),new_todo_text.text.toString(),false )

            addTodo(todo)
            findNavController().navigate(R.id.fragment)

        }
    }

    fun addTodo(todo:Todo){
        if ( new_todo_title.text.toString().trim().isBlank() ) {
            Toast.makeText(context, "Can not insert empty to do!", Toast.LENGTH_SHORT).show()
            return
        }

        var todoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)
        todoViewModel.insert(todo)
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddTodoFragment().apply {

            }
    }
}
