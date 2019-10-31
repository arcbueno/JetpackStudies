package com.example.mytodo.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.mytodo.Models.Todo
import com.example.mytodo.Models.TodoViewModel
import com.example.mytodo.R
import kotlinx.android.synthetic.main.fragment_todo_detail.*

class TodoDetail : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var binding:FragmentMainBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_todo_detail,container , false)
        var todoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)

        var id = arguments!!.getInt("todoId")
        var todo = getTodo(id, todoViewModel)

        detail_title.text = SpannableStringBuilder(todo?.Title)
        detail_text.text = SpannableStringBuilder(todo?.Text)

        detail_save.setOnClickListener{
            it.hideKeyboard()
            var newTodo = Todo(id,detail_title.text.toString(),detail_text.text.toString(),todo!!.Checked )

            addTodo(newTodo, todoViewModel)
            findNavController().navigate(R.id.action_fragment_todo_detail_to_fragment)
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_detail, container, false)

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)





    }

    fun addTodo(todo:Todo, todoViewModel:TodoViewModel){
        if ( detail_title.text.toString().trim().isBlank() ) {
            Toast.makeText(context, "Can not insert an empty To do!", Toast.LENGTH_SHORT).show()
            return
        }
        todoViewModel.insert(todo)
    }

    fun getTodo(id: Int, todoViewModel:TodoViewModel) : Todo? {
        return todoViewModel.getById(id)
    }


    fun View.hideKeyboard() {
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
