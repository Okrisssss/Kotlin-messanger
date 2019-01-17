package com.example.piachimov.firebasekotlinexample.ui.registration

interface RegistrationView {

    fun onRegistrationSuccess(message: String)

    fun onRegistrationFailed(message: String)

    fun onPhotoUploadedSuccess(message: String)

    fun onPhotoUploadedFailed(message: String)
}