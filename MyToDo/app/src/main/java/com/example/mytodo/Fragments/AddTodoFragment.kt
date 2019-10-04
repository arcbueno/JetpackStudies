package com.example.mytodo.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mytodo.Db.TodoDbHelper
import com.example.mytodo.Models.Todo
import com.example.mytodo.Models.TodoList

import com.example.mytodo.R
import kotlinx.android.synthetic.main.fragment_add_todo.*


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AddTodoFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AddTodoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddTodoFragment : Fragment() {

    lateinit var todoDbHelpser : TodoDbHelper

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
            var todo = Todo(12,new_todo_title.text.toString(),new_todo_text.text.toString(),false )

            todoDbHelpser = context?.let { it1 -> TodoDbHelper(it1) }!!

            var result = todoDbHelpser.InsertTodo(todo)
            if(result){
                findNavController().navigate(R.id.action_fragment_add_todo_to_fragment)
            }

        }


    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
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
         * @return A new instance of fragment AddTodoFragment.
         */
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddTodoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
