package com.example.piachimov.firebasekotlinexample.ui.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.piachimov.firebasekotlinexample.R
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.ui.latestMessages.LatestMessagesActivity
import com.example.piachimov.firebasekotlinexample.ui.main.MainActivity
import com.example.piachimov.firebasekotlinexample.ui.registration.RegistrationActivity
import com.example.piachimov.firebasekotlinexample.utils.ScreenNavigation
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import javax.inject.Inject


class LoginActivity : AppCompatActivity(), LoginView {

    @Inject
    lateinit var loginPresenter: LoginPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Injector.initLoginActivityComponent(this)
        Injector.loginActivityComponent?.inject(this)
        if (loginPresenter.mAuth?.currentUser != null) {
            ScreenNavigation.switchActivity(this, LatestMessagesActivity::class.java)
        }
    }


    fun onLogin(view: View) {
        val email = email_edit_text_login_screen.text.toString()
        val password = password_edit_text_login_screen.text.toString()
        loginPresenter.onLogin(email, password, this)
    }

    fun switchToRegisterActivity(view: View) {
        ScreenNavigation.switchActivity(this, RegistrationActivity::class.java)
        finish()
    }


    override fun onLoginSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        ScreenNavigation.switchActivity(this, LatestMessagesActivity::class.java)

    }

    override fun onLoginFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        Injector.releaseLoginActivityComponent()
        Injector.loginActivityComponent = null
        super.onDestroy()
    }
}
