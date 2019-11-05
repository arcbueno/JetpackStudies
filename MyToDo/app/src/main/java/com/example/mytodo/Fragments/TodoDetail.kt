package com.example.mytodo.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.mytodo.models.Todo
import com.example.mytodo.models.TodoViewModel
import com.example.mytodo.R
import com.example.mytodo.RecyclerViewUtils.MyViewHolder
import com.example.mytodo.databinding.FragmentTodoDetailBinding
import kotlinx.android.synthetic.main.fragment_todo_detail.*

class TodoDetail : Fragment() {

    lateinit var binding:FragmentTodoDetailBinding
    private lateinit var todoViewModel:TodoViewModel
    var id: Int? = null
    var todoData:Todo? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_todo_detail, container,false)
        binding.lifecycleOwner = this

        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)

        id = arguments!!.getInt("todoId")
        todoData = getTodo(id!!, todoViewModel)

        binding.todo = todoData

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detail_save.setOnClickListener{
            it.hideKeyboard()
            var newTodo = Todo(todoData!!.Id,detail_title.text.toString(),detail_text.text.toString(),todoData!!.Checked )

            addTodo(newTodo, todoViewModel)
            findNavController().navigate(R.id.action_fragment_todo_detail_to_fragment)
        }

        delete_item.setOnClickListener{
            it.hideKeyboard()
            deleteTodo(id!!, todoViewModel)
            findNavController().navigate(R.id.action_fragment_todo_detail_to_fragment)
        }

    }

    private fun addTodo(todo:Todo, todoViewModel:TodoViewModel){
        if ( detail_title.text.toString().trim().isBlank() ) {
            Toast.makeText(context, "Can not insert an empty To do!", Toast.LENGTH_SHORT).show()
            return
        }
        todoViewModel.insert(todo)
    }

    private fun deleteTodo(id:Int, todoViewModel: TodoViewModel){
        var todo = getTodo(id, todoViewModel)
        todoViewModel.delete(todo!!)
    }

    private fun getTodo(id: Int, todoViewModel:TodoViewModel) : Todo? {
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
