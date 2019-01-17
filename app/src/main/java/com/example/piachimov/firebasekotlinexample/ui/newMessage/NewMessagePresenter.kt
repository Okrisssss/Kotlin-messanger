package com.example.piachimov.firebasekotlinexample.ui.newMessage

import android.content.Context
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.model.User
import com.example.piachimov.firebasekotlinexample.ui.latestMessages.LatestMessagesActivity
import com.example.piachimov.firebasekotlinexample.utils.ScreenNavigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NewMessagePresenter(var newMessageView: NewMessageView, var context: Context) {

    val mAuth: FirebaseAuth? = Injector.appComponent?.firebaseAuth()
    private val firebaseDatabas: FirebaseDatabase? = Injector.appComponent?.firebaseDatabase()
    private val ref = firebaseDatabas?.getReference("users")
    var usersList: ArrayList<User> = arrayListOf()


    fun getDataFromfirebaseDatabas(): ArrayList<User> {
        ref?.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    usersList.clear()
                    for (user in dataSnapshot.children) {
                        val userItem = user.getValue(User::class.java)
                        usersList.add(userItem!!)
                    }
                    newMessageView.onSuccess(usersList)
                }
            }
        })
        return usersList
    }
}