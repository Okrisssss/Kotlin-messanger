package com.example.piachimov.firebasekotlinexample.ui.latestMessages

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.piachimov.firebasekotlinexample.R
import com.example.piachimov.firebasekotlinexample.model.Message
import com.example.piachimov.firebasekotlinexample.ui.chat.ChatActivity
import com.example.piachimov.firebasekotlinexample.utils.ScreenNavigation
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class LatestMessageRecyclerAdapter(var context: Context, var picasso: Picasso, var latestMessagesPresenter: LatestMessagesPresenter) : RecyclerView.Adapter<LatestMessageRecyclerAdapter.ViewHolder>() {

    var latestMessage = ArrayList<Message>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val last_message_user_photo = itemView.findViewById<CircleImageView>(R.id.last_message_user_photo)!!
        val last_message_user_name = itemView.findViewById<TextView>(R.id.last_message_user_name)!!
        val last_message_text_view = itemView.findViewById<TextView>(R.id.last_message_text_view)!!
    }

    fun showLatestMessagesList(messageItem: ArrayList<Message>) {
        this.latestMessage = messageItem
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.latest_message_item, p0, false)
        return LatestMessageRecyclerAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val latestMessagePosition = latestMessage[p1]
        p0.last_message_text_view.text = latestMessagePosition.message
        p0.last_message_user_name.text = latestMessagePosition.toUserName
        picasso.load(latestMessagePosition.photoUrlTo).into(p0.last_message_user_photo)
        p0.last_message_text_view.setOnClickListener { ScreenNavigation.switchWithDataFromLatestMessageActivity(context, ChatActivity::class.java, latestMessagePosition.photoUrlFrom, latestMessagePosition.toId, latestMessagePosition.toUserName, latestMessagePosition.photoUrlTo) }
    }

    override fun getItemCount(): Int {
        return latestMessage.size
    }
}