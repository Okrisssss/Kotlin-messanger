package com.example.piachimov.firebasekotlinexample.ui.chat

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.piachimov.firebasekotlinexample.R
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.model.Message
import com.example.piachimov.firebasekotlinexample.model.User
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class ChatRecyclerAdapter(var context: Context, var picasso: Picasso, var chatPresenter: ChatPresenter) : RecyclerView.Adapter<ChatRecyclerAdapter.ViewHolder>() {


    var messageList = ArrayList<Message>()
    val mAuth: FirebaseAuth? = Injector.appComponent?.firebaseAuth()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val message_text_view_to = itemView.findViewById<TextView>(R.id.message_text_view_to)!!
        val chat_user_photo_to = itemView.findViewById<ImageView>(R.id.chat_user_photo_to)!!
        val message_text_view_from = itemView.findViewById<TextView>(R.id.message_text_view_from)!!
        val chat_user_photo_from = itemView.findViewById<ImageView>(R.id.chat_user_photo_from)!!
    }

    fun showMessageList(messageItem: ArrayList<Message>) {
        this.messageList = messageItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ChatRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.chat_row_item, p0, false)
        return ChatRecyclerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val chatMessageItem = messageList[p1]
        if (chatMessageItem.fromId  != mAuth!!.uid){
            p0.message_text_view_from.text = chatMessageItem.message
            picasso.load(chatMessageItem.photoUrlFrom).into(p0.chat_user_photo_from)
            p0.message_text_view_to.visibility = View.GONE
            p0.chat_user_photo_to.visibility = View.GONE
        } else {
            p0.message_text_view_to.text = chatMessageItem.message
            picasso.load(chatMessageItem.photoUrlTo).into(p0.chat_user_photo_to)
            p0.message_text_view_from.visibility = View.GONE
            p0.chat_user_photo_from.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }
}