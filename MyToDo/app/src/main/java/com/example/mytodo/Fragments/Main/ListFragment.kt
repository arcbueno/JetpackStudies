package com.example.mytodo.Fragments.Main

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import com.example.mytodo.models.Todo
import com.example.mytodo.R
import com.example.mytodo.RecyclerViewUtils.MyAdapter
import kotlinx.android.synthetic.main.fragment_list.*


var listaTodo = ArrayList<Todo>()

class ListFragment : Fragment() {

    // Recebendo instância da viewmodel
    lateinit var listViewModel: ListFragmentViewModel;

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listViewModel = ViewModelProvider(this).get(ListFragmentViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Instanciando o adapter, que é usado para "adaptar" os itens da lista
        var myAdapter = MyAdapter(
            listViewModel,
            listViewModel.listaTodo
        )

        // Criando observer para atualizar a lista ao ter um novo item
        listViewModel.getAllTodos().observe(viewLifecycleOwner, Observer { myAdapter.setTodo(it) })

        // Definindo formato do layout da RecyclerView e também o adapter
        list_id.layoutManager = GridLayoutManager(activity, 2)
        list_id.adapter = myAdapter

        // Definindo função do onClick no FAB para navegar para a tela de criação
        fab_add.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_to_fragment_add_todo)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): ListFragment =
            ListFragment()
    }
}
