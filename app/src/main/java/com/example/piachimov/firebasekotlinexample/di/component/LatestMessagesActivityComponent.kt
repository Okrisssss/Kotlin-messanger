package com.example.piachimov.firebasekotlinexample.di.component

import com.example.piachimov.firebasekotlinexample.di.module.LatestMessagesActivityModule
import com.example.piachimov.firebasekotlinexample.di.scope.ActivityScope
import com.example.piachimov.firebasekotlinexample.ui.latestMessages.LatestMessagesActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [LatestMessagesActivityModule::class])
interface LatestMessagesActivityComponent {
    fun inject(latestMessagesActivity: LatestMessagesActivity)

}