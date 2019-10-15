package com.example.mytodo.Fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.example.mytodo.Db.MyApplication
import com.example.mytodo.Models.IListaTodo
import com.example.mytodo.Models.Todo
import com.example.mytodo.Models.TodoList
import com.example.mytodo.Models.TodoViewModel
import com.example.mytodo.R
import com.example.mytodo.RecyclerViewUtils.MyAdapter
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.list_item.*


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [ListFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

var listaTodo = ArrayList<Todo>()

class ListFragment : Fragment() {
    var todoList = TodoList().toDos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
            = inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var context = this

        var todoViewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)

        var myadapter = MyAdapter(todoViewModel)
        todoViewModel.getAllTodos().observe(context, Observer { myadapter.setTodo(it) })


        var recyclerView = list_id

        recyclerView.layoutManager = GridLayoutManager(activity, 2) as RecyclerView.LayoutManager?
        recyclerView.adapter = myadapter

        fragment_add_todo.setOnClickListener{
            findNavController().navigate(R.id.action_fragment_to_fragment_add_todo)
        }
    }

    fun addTodo(title:String, text: String, checked:Boolean){

        var todo = Todo(null, title, text, checked)
        MyApplication.database?.todoDao()?.insertTodo(todo)
    }

    fun Populate(){
        for ( i in todoList){
            addTodo(i.Title, i.Text, i.Checked)
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() : ListFragment = ListFragment()
    }
}
