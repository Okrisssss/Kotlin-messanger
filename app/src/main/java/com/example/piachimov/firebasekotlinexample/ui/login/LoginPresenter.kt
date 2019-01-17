package com.example.piachimov.firebasekotlinexample.ui.login

import android.content.Context
import android.widget.Toast
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.google.firebase.auth.FirebaseAuth

class LoginPresenter(var loginView: LoginView, var context: Context) {


    val mAuth: FirebaseAuth? = Injector.appComponent?.firebaseAuth()

    fun onLogin(email: String, password: String, loginActivity: LoginActivity) {
        if (!email.isEmpty() && !password.isEmpty()) {
            mAuth?.signInWithEmailAndPassword(email, password)?.addOnCompleteListener(loginActivity) { task ->
                if (task.isSuccessful) {
                    loginView.onLoginSuccess("Login Success")
                } else {
                    loginView.onLoginFailed("Login failed, something went wrong!")
                }
            }
        } else {
            Toast.makeText(context, "Please fill up all required fields", Toast.LENGTH_SHORT).show()
        }
    }
}