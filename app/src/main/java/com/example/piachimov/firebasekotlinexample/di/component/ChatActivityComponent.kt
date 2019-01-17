package com.example.piachimov.firebasekotlinexample.di.component

import com.example.piachimov.firebasekotlinexample.di.module.ChatActivityModule
import com.example.piachimov.firebasekotlinexample.di.scope.ActivityScope
import com.example.piachimov.firebasekotlinexample.ui.chat.ChatActivity
import dagger.Component


@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ChatActivityModule::class])
interface ChatActivityComponent {
    fun inject(chatActivity: ChatActivity)
}