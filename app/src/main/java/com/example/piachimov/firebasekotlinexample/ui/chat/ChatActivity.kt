package com.example.piachimov.firebasekotlinexample.ui.chat

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.piachimov.firebasekotlinexample.BuildConfig
import com.example.piachimov.firebasekotlinexample.R
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.model.User
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_chat.*
import javax.inject.Inject

class ChatActivity : AppCompatActivity(), ChatView {

    @Inject
    lateinit var chatPresenter: ChatPresenter
    @Inject
    lateinit var chatRecyclerAdapter: ChatRecyclerAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        Injector.initChatActivityComponent(this)
        Injector.chatActivityComponent?.inject(this)

        val companionUser = intent.getParcelableExtra<User>(BuildConfig.COMPANION_KEY)
        supportActionBar?.title = companionUser.username
        val groupieAdapter = GroupAdapter<ViewHolder>()
        chat_recycler_view.adapter = groupieAdapter


        groupieAdapter.add(ChatFromItem())
        groupieAdapter.add(ChatToItem())
        groupieAdapter.add(ChatFromItem())
        groupieAdapter.add(ChatToItem())
        groupieAdapter.add(ChatFromItem())
        groupieAdapter.add(ChatToItem())
        groupieAdapter.add(ChatFromItem())
        groupieAdapter.add(ChatToItem())
        groupieAdapter.add(ChatFromItem())
        groupieAdapter.add(ChatToItem())
        groupieAdapter.add(ChatFromItem())
        groupieAdapter.add(ChatToItem())
        groupieAdapter.add(ChatFromItem())
        groupieAdapter.add(ChatToItem())
        groupieAdapter.add(ChatFromItem())
        groupieAdapter.add(ChatToItem())

    }

    class ChatFromItem : Item<ViewHolder>() {

        override fun getLayout(): Int {
            return R.layout.chat_from_row
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {

        }

    }

    class ChatToItem : Item<ViewHolder>() {

        override fun getLayout(): Int {
            return R.layout.chat_to_row
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {

        }

    }

    override fun onSuccess(message: String) {

    }

    override fun onFailure(message: String) {

    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.releaseChatActivityComponent()
    }
}
