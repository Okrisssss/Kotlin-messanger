package com.example.piachimov.firebasekotlinexample.ui.newMessage

import com.example.piachimov.firebasekotlinexample.model.User

interface NewMessageView {

    fun onSuccess(list: ArrayList<User>)

    fun onFailure(message: String)

    fun onGetCurrentUser(currentUser: User)
}