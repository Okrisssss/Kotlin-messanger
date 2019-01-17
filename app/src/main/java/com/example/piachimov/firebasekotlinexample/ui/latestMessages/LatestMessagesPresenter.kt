package com.example.piachimov.firebasekotlinexample.ui.latestMessages

import android.content.Context
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.ui.login.LoginActivity
import com.example.piachimov.firebasekotlinexample.utils.ScreenNavigation
import com.google.firebase.auth.FirebaseAuth

class LatestMessagesPresenter(var latestMessagesView: LatestMessagesView, var context: Context) {

    private val mAuth: FirebaseAuth? = Injector.appComponent?.firebaseAuth()


    fun logOut() {
        mAuth?.signOut()
        ScreenNavigation.switchActivity(context, LoginActivity::class.java)
    }
}