package com.example.piachimov.firebasekotlinexample.ui.newMessage

import android.content.Context
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class NewMessagePresenter(var newMessageView: NewMessageView, var context: Context) {

    val mAuth: FirebaseAuth? = Injector.appComponent?.firebaseAuth()
    private val uidOfCurrentUser = mAuth!!.uid

    private val firebaseDatabase: FirebaseDatabase? = Injector.appComponent?.firebaseDatabase()
    private val ref = firebaseDatabase?.getReference("users")



    var usersList: ArrayList<User> = arrayListOf()


    companion object {
        var currentUser: User? = null
    }


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


    fun getCurrentUser(userList: ArrayList<User>): User{

        for (currentuser123 in userList){
            if (currentuser123.id == uidOfCurrentUser){
                currentUser = currentuser123
                newMessageView.onGetCurrentUser(currentUser!!)
            }

        }
            return  currentUser!!
    }
}