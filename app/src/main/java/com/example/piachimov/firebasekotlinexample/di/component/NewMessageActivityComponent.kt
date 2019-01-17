package com.example.piachimov.firebasekotlinexample.di.component

import com.example.piachimov.firebasekotlinexample.di.module.NewMessageActivityModule
import com.example.piachimov.firebasekotlinexample.di.scope.ActivityScope
import com.example.piachimov.firebasekotlinexample.ui.newMessage.NewMessageActivity
import dagger.Component


@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [NewMessageActivityModule::class])
interface NewMessageActivityComponent {
    fun inject(newMessageActivity: NewMessageActivity)
}