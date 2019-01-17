package com.example.piachimov.firebasekotlinexample.ui.chat

interface ChatView {

    fun onSuccess(message: String)

    fun onFailure(message: String)
}