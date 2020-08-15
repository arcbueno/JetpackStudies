package com.example.mytodo.Fragments.TodoDetail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.mytodo.models.Todo
import com.example.mytodo.R
import com.example.mytodo.databinding.FragmentTodoDetailBinding
import kotlinx.android.synthetic.main.fragment_todo_detail.*

class TodoDetail : Fragment() {

    lateinit var binding: FragmentTodoDetailBinding
    lateinit var todoViewModel: TodoDetailViewModel
    var id: Int? = null
    var todoData: Todo? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        todoViewModel = ViewModelProvider(this).get(TodoDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.fragment_todo_detail, container, false)
        binding.lifecycleOwner = this

        id = arguments!!.getInt("todoId")

        binding.todo = getTodo(id!!)
        todoData = binding.todo
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab_save.setOnClickListener {
            it.hideKeyboard()
            var newTodo = Todo(
                todoData!!.Id,
                update_todo_title.text.toString(),
                update_todo_text.text.toString(),
                todoData!!.Checked
            )

            var success = addTodo(newTodo)
            if (success)
                findNavController().navigate(R.id.action_fragment_todo_detail_to_fragment)
        }

        fab_delete.setOnClickListener {
            it.hideKeyboard()
            deleteTodo(id!!)
            findNavController().navigate(R.id.action_fragment_todo_detail_to_fragment)
        }

    }

    private fun addTodo(todo: Todo): Boolean {
        if (update_todo_title.text.toString().trim().isBlank()) {
            Toast.makeText(context, "Cannot insert an empty To do!", Toast.LENGTH_SHORT).show()
            return false
        }
        todoViewModel.insert(todo)
        return true
    }

    private fun deleteTodo(id: Int) {
        var todo = getTodo(id)
        todoViewModel.delete(todo!!)
    }

    private fun getTodo(id: Int): Todo? {
        return todoViewModel.getById(id)
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            TodoDetail().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
