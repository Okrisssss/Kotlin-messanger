package com.example.piachimov.firebasekotlinexample.ui.registration

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.example.piachimov.firebasekotlinexample.R
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.ui.latestMessages.LatestMessagesActivity
import com.example.piachimov.firebasekotlinexample.ui.login.LoginActivity
import com.example.piachimov.firebasekotlinexample.utils.ScreenNavigation
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.activity_registration.*
import org.jetbrains.anko.toast
import javax.inject.Inject


class RegistrationActivity : AppCompatActivity(), RegistrationView {

    @Inject
    lateinit var registrationPresenter: RegistrationPresenter

    var selectedPhotoUri: Uri? = null
    var placeholderUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        Injector.initRegistrationActivityComponent(this)
        Injector.registrationActivityComponent?.inject(this)
    }

    fun onRegister(view: View) {
        val username = username_edit_text_registration_screen.text.toString()
        val email = email_edit_text_registration_screen.text.toString()
        val password = password_edit_text_registration_screen.text.toString()
        if (selectedPhotoUri != null) {
        registrationPresenter.onRegister(email, username, password, this)
            registrationPresenter.saveUserData(username, selectedPhotoUri!!)
        } else {
            Toast.makeText(this, this.getString(R.string.choose_the_photo), Toast.LENGTH_SHORT).show()
        }
    }

    fun onSelectPhoto(view: View) {
        startActivityForResult(ScreenNavigation.photoSelector(), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            selectedPhotoUri = data.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
            select_photo_image_view_registration_screen.setImageBitmap(bitmap)
            select_photo_button.alpha = 0f
        }
    }


    override fun onRegistrationSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        ScreenNavigation.switchActivity(this, LatestMessagesActivity::class.java)
    }

    override fun onRegistrationFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }


    fun onSwitchToLoginActivity(view: View) {
        ScreenNavigation.switchActivity(this, LoginActivity::class.java)

    }

    override fun onPhotoUploadedSuccess(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onPhotoUploadedFailed(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        Injector.releaseRegistrationActivityComponent()
        Injector.registrationActivityComponent = null
        super.onDestroy()
    }
}
