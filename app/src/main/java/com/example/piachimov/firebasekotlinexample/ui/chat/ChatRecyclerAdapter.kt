package com.example.piachimov.firebasekotlinexample.ui.chat

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.piachimov.firebasekotlinexample.R
import com.example.piachimov.firebasekotlinexample.model.Message
import com.squareup.picasso.Picasso

class ChatRecyclerAdapter(var context: Context, var picasso: Picasso, var chatPresenter: ChatPresenter) : RecyclerView.Adapter<ChatRecyclerAdapter.ViewHolder>() {


    var list = ArrayList<Message>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val message_text_view = itemView.findViewById<TextView>(R.id.message_text_view)!!
    }

    fun showList(items: ArrayList<Message>) {
        this.list = items
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChatRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.chat_from_row, p0, false)
        return ChatRecyclerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val chatMessageItem = list[p1]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}