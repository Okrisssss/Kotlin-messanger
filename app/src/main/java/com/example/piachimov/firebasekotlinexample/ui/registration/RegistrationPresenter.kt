package com.example.piachimov.firebasekotlinexample.ui.registration

import android.content.Context
import android.net.Uri
import android.widget.Toast
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*
import android.provider.MediaStore
import android.util.Log


class RegistrationPresenter(var registrationView: RegistrationView, var context: Context) {

    private val mAuth: FirebaseAuth? = Injector.appComponent?.firebaseAuth()
    private val firebaseDatabase: FirebaseDatabase? = Injector.appComponent?.firebaseDatabase()
    private val refUsers = firebaseDatabase?.getReference("users")
    private val firebaseStorage: FirebaseStorage? = Injector.appComponent?.firebaseStorage()
    val filename = UUID.randomUUID().toString()
    private val refStorage = firebaseStorage?.getReference("/images/$filename")




    fun onRegister(email: String, name: String, password: String, registrationActivity: RegistrationActivity) {
        if (!email.isEmpty() && !name.isEmpty() && !password.isEmpty()) {
            mAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(registrationActivity) {
                if (!it.isSuccessful) return@addOnCompleteListener
                registrationView.onRegistrationSuccess("Successfully created user!")
            }.addOnFailureListener {
                registrationView.onRegistrationFailed("Failed to create user: ${it.message.toString()}")
            }
        } else {
            Toast.makeText(context, "Please fill up all required fields", Toast.LENGTH_SHORT).show()
        }
    }

    fun saveUserData(name: String, selectedPhotoUri: Uri) {
        refStorage?.putFile(selectedPhotoUri)?.addOnSuccessListener {
            registrationView.onPhotoUploadedSuccess("Photo was uploaded successfully")
            refStorage?.downloadUrl?.addOnSuccessListener { it ->
                val userId = refUsers?.push()?.key
                val user = User(userId!!, name, it.toString())
                refUsers?.child(userId)?.setValue(user)
            } }?.addOnFailureListener {
        }
    }
}
