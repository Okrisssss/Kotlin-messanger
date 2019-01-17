package com.example.piachimov.firebasekotlinexample.di

import com.example.piachimov.firebasekotlinexample.AppActivity
import com.example.piachimov.firebasekotlinexample.di.component.*
import com.example.piachimov.firebasekotlinexample.di.module.*
import com.example.piachimov.firebasekotlinexample.ui.chat.ChatActivity

import com.example.piachimov.firebasekotlinexample.ui.latestMessages.LatestMessagesActivity
import com.example.piachimov.firebasekotlinexample.ui.login.LoginActivity
import com.example.piachimov.firebasekotlinexample.ui.main.MainActivity
import com.example.piachimov.firebasekotlinexample.ui.newMessage.NewMessageActivity
import com.example.piachimov.firebasekotlinexample.ui.registration.RegistrationActivity

object Injector {

    var appComponent: AppComponent? = null
    var mainActivityComponent: MainActivityComponent? = null
    var loginActivityComponent: LoginActivityComponent? = null
    var registrationActivityComponent: RegistrationActivityComponent? = null
    var latestMessagesActivityComponent: LatestMessagesActivityComponent? = null
    var newMessagesActivityComponent: NewMessageActivityComponent? = null
    var chatActivityComponent: ChatActivityComponent? = null

    fun initAppComponent(appActivity: AppActivity) {
        appComponent = appComponent ?: DaggerAppComponent.builder()
                .appModule(AppModule(appActivity))
                .build()
    }

    fun initMainActivityComponent(mainActivity: MainActivity) {
        mainActivityComponent = mainActivityComponent ?: DaggerMainActivityComponent.builder()
                .appComponent(appComponent)
                .mainActivityModule(MainActivityModule(mainActivity))
                .build()
    }

    fun initLoginActivityComponent(loginActivity: LoginActivity){
        loginActivityComponent = loginActivityComponent ?: DaggerLoginActivityComponent.builder()
                .appComponent(appComponent)
                .loginActivityModule(LoginActivityModule(loginActivity))
                .build()
    }

    fun initRegistrationActivityComponent(registrationActivity: RegistrationActivity){
        registrationActivityComponent = registrationActivityComponent ?: DaggerRegistrationActivityComponent.builder()
                .appComponent(appComponent)
                .registrationActivityModule(RegistrationActivityModule(registrationActivity))
                .build()
    }

    fun initLatestMessagesActivityComponent(latestMessagesActivity: LatestMessagesActivity){
        latestMessagesActivityComponent = latestMessagesActivityComponent ?: DaggerLatestMessagesActivityComponent.builder()
                .appComponent(appComponent)
                .latestMessagesActivityModule(LatestMessagesActivityModule(latestMessagesActivity))
                .build()
    }

    fun initNewMessageActivityComponent(newMessageActivity: NewMessageActivity){
       newMessagesActivityComponent = newMessagesActivityComponent ?: DaggerNewMessageActivityComponent.builder()
               .appComponent(appComponent)
               .newMessageActivityModule(NewMessageActivityModule(newMessageActivity))
               .build()
    }

    fun initChatActivityComponent(chatActivity: ChatActivity){
        chatActivityComponent = chatActivityComponent ?: DaggerChatActivityComponent.builder()
                .appComponent(appComponent)
                .chatActivityModule(ChatActivityModule(chatActivity))
                .build()
    }


    fun releaseMainActivityComponent() {
        mainActivityComponent = null
    }

    fun releaseLoginActivityComponent(){
        loginActivityComponent = null
    }

    fun releaseRegistrationActivityComponent(){
        registrationActivityComponent = null
    }

    fun releaseLatestMessageActivityComponent(){
        latestMessagesActivityComponent = null
    }

    fun releaseNewMessageActivityComponent(){
        newMessagesActivityComponent = null
    }

    fun releaseChatActivityComponent(){
        chatActivityComponent = null
    }

}