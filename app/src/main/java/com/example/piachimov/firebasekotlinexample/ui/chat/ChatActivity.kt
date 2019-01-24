package com.example.piachimov.firebasekotlinexample.ui.chat

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.example.piachimov.firebasekotlinexample.BuildConfig
import com.example.piachimov.firebasekotlinexample.R
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.model.Message
import com.example.piachimov.firebasekotlinexample.model.User
import com.example.piachimov.firebasekotlinexample.utils.StringUtils
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_new_message.*
import javax.inject.Inject

class ChatActivity : AppCompatActivity(), ChatView {


    @Inject
    lateinit var chatPresenter: ChatPresenter
    @Inject
    lateinit var chatRecyclerAdapter: ChatRecyclerAdapter


    private lateinit var currentUserPhotoUrl: String
    private lateinit var companionUserId: String
    private lateinit var companionUserName: String
    private lateinit var companionUserPhotoUrls: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        Injector.initChatActivityComponent(this)
        Injector.chatActivityComponent?.inject(this)


        currentUserPhotoUrl = intent.getStringExtra("1")
        companionUserId = intent.getStringExtra("2")
        companionUserName = intent.getStringExtra("3")
        companionUserPhotoUrls = intent.getStringExtra("4")

        supportActionBar?.title = companionUserName
        initRecyclerView()
        chatPresenter.listenForMessages(companionUserId)
    }


    fun initRecyclerView() {
        chat_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        chat_recycler_view.adapter = chatRecyclerAdapter
    }

    override fun onMessagesFetchSuccess(messageList: ArrayList<Message>) {
        chatRecyclerAdapter.showMessageList(messageList)
        chat_recycler_view.scrollToPosition(chatRecyclerAdapter.itemCount -1)
    }

    override fun onMessagesFetchFailure(message: String) {

    }


    fun sendMessage(view: View) {
        val message = enter_message_edit_text_chat_screen.text.toString()
        if (StringUtils.isEmpty(message)){
            Toast.makeText(this, "Please type the message", Toast.LENGTH_SHORT).show()
        } else {
            chatPresenter.sendMessage(message, companionUserId, companionUserPhotoUrls, currentUserPhotoUrl, companionUserName)
            chatPresenter.listenForMessages(companionUserId)
            enter_message_edit_text_chat_screen.text.clear()
        }

    }

    override fun onSuccess(message: String) {

    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }


    override fun onDestroy() {
        super.onDestroy()
        Injector.releaseChatActivityComponent()
    }
}
