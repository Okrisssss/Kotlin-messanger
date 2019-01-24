package com.example.piachimov.firebasekotlinexample.ui.latestMessages

import com.example.piachimov.firebasekotlinexample.model.Message

interface LatestMessagesView {

    fun onSuccess(messageList: ArrayList<Message>)

    fun onFailure(message: String)
}