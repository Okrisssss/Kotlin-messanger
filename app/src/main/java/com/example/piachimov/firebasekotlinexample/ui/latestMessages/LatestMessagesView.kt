package com.example.piachimov.firebasekotlinexample.ui.latestMessages

interface LatestMessagesView {

    fun onSuccess(message: String)

    fun onFailure(message: String)
}