package com.example.piachimov.firebasekotlinexample.ui.chat

import com.example.piachimov.firebasekotlinexample.model.Message
import com.example.piachimov.firebasekotlinexample.model.User

interface ChatView {

    fun onSuccess(message: String)

    fun onFailure(message: String)

    fun onMessagesFetchSuccess(messageList: ArrayList<Message>)

    fun onMessagesFetchFailure(message: String)

}