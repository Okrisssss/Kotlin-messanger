package com.example.piachimov.firebasekotlinexample.di.module

import com.example.piachimov.firebasekotlinexample.di.scope.ActivityScope
import com.example.piachimov.firebasekotlinexample.ui.latestMessages.LatestMessageRecyclerAdapter
import com.example.piachimov.firebasekotlinexample.ui.latestMessages.LatestMessagesActivity
import com.example.piachimov.firebasekotlinexample.ui.latestMessages.LatestMessagesPresenter
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides


@Module
class LatestMessagesActivityModule(private val latestMessagesActivity: LatestMessagesActivity) {

    @ActivityScope
    @Provides
    fun provideLatestMessagePresenter(): LatestMessagesPresenter = LatestMessagesPresenter(latestMessagesActivity, latestMessagesActivity.baseContext)


    @ActivityScope
    @Provides
    fun provideLatestMessageAdapter(picasso: Picasso, latestMessagesPresenter: LatestMessagesPresenter): LatestMessageRecyclerAdapter = LatestMessageRecyclerAdapter(latestMessagesActivity, picasso, latestMessagesPresenter)
}