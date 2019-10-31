package com.example.mytodo.RecyclerViewUtils

import android.content.DialogInterface
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import java.text.FieldPosition

interface OnItemClickListener{
    fun onItemClicked(position: Int, view: View)
}

fun RecyclerView.addOnItemClickListener(onClickListener: OnItemClickListener){
    this.addOnChildAttachStateChangeListener(object: RecyclerView.OnChildAttachStateChangeListener {


        override fun onChildViewDetachedFromWindow(view: View) {
            view?.setOnClickListener(null)
        }

        override fun onChildViewAttachedToWindow(view: View) {
            view?.setOnClickListener {
                val holder = getChildViewHolder(view)
                onClickListener.onItemClicked(holder.adapterPosition, view)
            }

        }

    })
}


















