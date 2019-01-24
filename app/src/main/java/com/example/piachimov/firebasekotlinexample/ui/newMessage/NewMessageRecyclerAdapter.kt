package com.example.piachimov.firebasekotlinexample.ui.newMessage

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.piachimov.firebasekotlinexample.R
import com.example.piachimov.firebasekotlinexample.model.User
import com.example.piachimov.firebasekotlinexample.ui.chat.ChatActivity
import com.example.piachimov.firebasekotlinexample.utils.ScreenNavigation
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class NewMessageRecyclerAdapter(var context: Context, var picasso: Picasso, var newMessagePresenter: NewMessagePresenter) : RecyclerView.Adapter<NewMessageRecyclerAdapter.ViewHolder>() {

    var list = ArrayList<User>()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val user_image_new_message_screen = itemView.findViewById<CircleImageView>(R.id.user_image_new_message_screen)!!
        val username_text_view_new_message_screen = itemView.findViewById<TextView>(R.id.username_text_view_new_message_screen)!!
    }

    fun showList(items: ArrayList<User>) {
        this.list = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.user_item, p0, false)
        return NewMessageRecyclerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val userPosition = list[p1]
        p0.username_text_view_new_message_screen.text = userPosition.username
        picasso.load(userPosition.profileImageUrl).into(p0.user_image_new_message_screen)
        p0.username_text_view_new_message_screen.setOnClickListener { ScreenNavigation.switchWithDataFromLatestMessageActivity(context, ChatActivity::class.java,newMessagePresenter.getCurrentUser(list).username,userPosition.id, userPosition.username, userPosition.profileImageUrl )}
    }



    override fun getItemCount(): Int {
        return list.size
    }
}