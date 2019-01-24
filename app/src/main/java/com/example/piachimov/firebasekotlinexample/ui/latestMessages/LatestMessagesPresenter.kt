package com.example.piachimov.firebasekotlinexample.ui.latestMessages

import android.content.Context
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.model.Message
import com.example.piachimov.firebasekotlinexample.model.User
import com.example.piachimov.firebasekotlinexample.ui.login.LoginActivity
import com.example.piachimov.firebasekotlinexample.utils.ScreenNavigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class LatestMessagesPresenter(var latestMessagesView: LatestMessagesView, var context: Context) {

    val mAuth: FirebaseAuth? = Injector.appComponent?.firebaseAuth()
    private val uidOfCurrentUser = mAuth!!.uid
    private val firebaseDatabase: FirebaseDatabase? = Injector.appComponent?.firebaseDatabase()
    var latestMessagesList: ArrayList<Message> = arrayListOf()

    companion object {
        var currentUser: User? = null
    }


    fun logOut() {
        mAuth?.signOut()
        ScreenNavigation.switchActivity(context, LoginActivity::class.java)
    }


    fun listenForLatestMessages(): ArrayList<Message> {
        val fromId = mAuth!!.uid
        val refLatestTextMessages = firebaseDatabase?.getReference("/latest-messages/$fromId")

        refLatestTextMessages?.addChildEventListener(object : ChildEventListener {

            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onChildMoved(p0: DataSnapshot, p1: String?) {

            }

            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
                latestMessagesList.clear()
                val messagesEntry = p0.getValue(Message::class.java) ?: return
                latestMessagesList.add(messagesEntry)
                latestMessagesView.onSuccess(latestMessagesList)
            }

            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
                if (p0.exists()) {
                    latestMessagesList.clear()
                    val messagesEntry = p0.getValue(Message::class.java) ?: return
                    latestMessagesList.add(messagesEntry)
                    latestMessagesView.onSuccess(latestMessagesList)
                }
            }

            override fun onChildRemoved(p0: DataSnapshot) {

            }

        })
        return latestMessagesList
    }
}