package com.example.piachimov.firebasekotlinexample.model

class Message (val message: String, val messageID: String, val fromId: String, val toId: String, val timeStap: Long, val photoUrlTo: String, val photoUrlFrom: String, val toUserName: String){
    constructor() : this("", "", "", "", 1L, "", "", "")
}