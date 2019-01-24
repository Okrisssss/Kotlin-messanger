package com.example.piachimov.firebasekotlinexample.ui.chat

import android.content.Context
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.model.Message
import com.example.piachimov.firebasekotlinexample.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ChatPresenter(var chatView: ChatView, var context: Context) {

    private val mAuth: FirebaseAuth? = Injector.appComponent?.firebaseAuth()
    private val firebaseDatabase: FirebaseDatabase? = Injector.appComponent?.firebaseDatabase()


    var messagesList: ArrayList<Message> = arrayListOf()

    fun sendMessage(messageText: String, toId: String, photoUrlTo: String, photoUrFrom: String, toUserName: String ){
        val fromId = mAuth!!.uid
        val fromRefTextMessages = firebaseDatabase?.getReference("/user-messages/$fromId/$toId")
        val toRefTextMessages = firebaseDatabase?.getReference("/user-messages/$toId/$fromId")
        val toLatestMessageRef = firebaseDatabase?.getReference("/latest-messages/$fromId/$toId")
        val fromLatestMessageRef = firebaseDatabase?.getReference("/latest-messages/$toId/$fromId")
        val messageId = fromRefTextMessages?.push()?.key

        val message = Message(messageText, messageId.toString(), fromId!!, toId, System.currentTimeMillis() / 1000, photoUrlTo, photoUrFrom, toUserName )
        fromRefTextMessages?.child(messageId!!)?.setValue(message)?.addOnSuccessListener {
            chatView.onSuccess("Message was sent!!")
        }?.addOnFailureListener { chatView.onFailure("Message wasn't sent") }


        toRefTextMessages?.child(messageId!!)?.setValue(message)
        toLatestMessageRef?.setValue(message)
        fromLatestMessageRef?.setValue(message)

    }


        fun listenForMessages(toId: String): ArrayList<Message> {
            val fromId = mAuth!!.uid
            val refTextMessages = firebaseDatabase?.getReference("/user-messages/$fromId/$toId")
            refTextMessages?.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {

                }

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if (dataSnapshot.exists()) {
                        messagesList.clear()
                        for (message in dataSnapshot.children) {
                            val messageItem = message.getValue(Message::class.java)
                            messagesList.add(messageItem!!)
                        }
                        chatView.onMessagesFetchSuccess(messagesList)
                    }
                }
            })
            return messagesList
        }


    }
