package com.example.mytodo.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.example.mytodo.Db.MyApplication
import com.example.mytodo.Models.Todo
import com.example.mytodo.Models.TodoList
import com.example.mytodo.R
import com.example.mytodo.RecyclerViewUtils.MyAdapter
import kotlinx.android.synthetic.main.fragment_list.*


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

        //Populate()
        updateList()

        fragment_add_todo.setOnClickListener{
            findNavController().navigate(R.id.action_fragment_to_fragment_add_todo)
        }

    }

    override fun onResume() {
        super.onResume()
        updateList()
    }

    private fun updateList(){


        var lista = MyApplication.database?.todoDao()?.getAllTodos() as ArrayList<Todo>

        for(i in 0 until lista.size){
            if(!listaTodo.contains(lista[i])){
                listaTodo.add(lista[i])
            }
        }

        list_id.apply {
            layoutManager = GridLayoutManager(activity, 2) as RecyclerView.LayoutManager?
            adapter = MyAdapter(listaTodo)
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        @JvmStatic
        fun newInstance() : ListFragment = ListFragment()
    }
}
