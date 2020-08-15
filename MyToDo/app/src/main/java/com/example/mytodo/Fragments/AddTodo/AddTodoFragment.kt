package com.example.mytodo.Fragments.AddTodo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.mytodo.models.Todo

import com.example.mytodo.R
import kotlinx.android.synthetic.main.fragment_add_todo.*

class AddTodoFragment : Fragment() {

    lateinit var addTodoViewModel: AddTodoViewModel;

    override fun onAttach(context: Context) {
        super.onAttach(context)
        addTodoViewModel = ViewModelProvider(this).get(AddTodoViewModel::class.java)
    }

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

        fab_new.setOnClickListener {
            it.hideKeyboard()
            var todo =
                Todo(null, new_todo_title.text.toString(), new_todo_text.text.toString(), false)

            var success = addTodo(todo)
            if (success) {
                findNavController().navigate(R.id.fragment)
            }

        }
    }

    fun addTodo(todo: Todo): Boolean {
        if (new_todo_title.text.toString().trim().isBlank()) {
            Toast.makeText(context, "Cannot insert empty to do!", Toast.LENGTH_SHORT).show()
            return false
        }

        addTodoViewModel.insert(todo)
        return true;
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    companion object {

        @JvmStatic
        fun newInstance() = AddTodoFragment()
    }
}
