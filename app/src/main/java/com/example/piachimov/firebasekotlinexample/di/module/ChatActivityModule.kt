package com.example.piachimov.firebasekotlinexample.di.module

import com.example.piachimov.firebasekotlinexample.di.scope.ActivityScope
import com.example.piachimov.firebasekotlinexample.ui.chat.ChatActivity
import com.example.piachimov.firebasekotlinexample.ui.chat.ChatPresenter
import com.example.piachimov.firebasekotlinexample.ui.chat.ChatRecyclerAdapter
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides


@Module
class ChatActivityModule(private val chatActivity: ChatActivity) {

    @ActivityScope
    @Provides
    fun provideChatPresenter(): ChatPresenter = ChatPresenter(chatActivity, chatActivity.baseContext)


    @ActivityScope
    @Provides
    fun provideChatRecyclerAdapter(picasso: Picasso, chatPresenter: ChatPresenter): ChatRecyclerAdapter = ChatRecyclerAdapter(chatActivity, picasso, chatPresenter)

}