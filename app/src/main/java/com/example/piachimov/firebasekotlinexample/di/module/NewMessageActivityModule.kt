package com.example.piachimov.firebasekotlinexample.di.module

import com.example.piachimov.firebasekotlinexample.di.scope.ActivityScope
import com.example.piachimov.firebasekotlinexample.ui.newMessage.NewMessageActivity
import com.example.piachimov.firebasekotlinexample.ui.newMessage.NewMessagePresenter
import com.example.piachimov.firebasekotlinexample.ui.newMessage.NewMessageRecyclerAdapter
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides


@Module
class NewMessageActivityModule(private val newMessageActivity: NewMessageActivity) {

    @ActivityScope
    @Provides
    fun provideNewMesssagePresenter(): NewMessagePresenter = NewMessagePresenter(newMessageActivity, newMessageActivity.baseContext)


    @ActivityScope
    @Provides
    fun provideNewMessageRecyclerAdapter(picasso: Picasso, newMessagePresenter: NewMessagePresenter): NewMessageRecyclerAdapter = NewMessageRecyclerAdapter(newMessageActivity, picasso, newMessagePresenter)
}