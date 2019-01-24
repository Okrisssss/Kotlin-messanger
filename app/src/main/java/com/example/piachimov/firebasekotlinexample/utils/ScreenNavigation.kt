package com.example.piachimov.firebasekotlinexample.utils

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import com.example.piachimov.firebasekotlinexample.BuildConfig
import com.example.piachimov.firebasekotlinexample.model.Message
import com.example.piachimov.firebasekotlinexample.model.User
import com.example.piachimov.firebasekotlinexample.ui.newMessage.NewMessageActivity
import java.io.ByteArrayOutputStream


object ScreenNavigation {

    fun switchActivity(context: Context, activity: Class<out AppCompatActivity>) {
        val intent = Intent(context, activity)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun photoSelector() : Intent{
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        return  intent
    }

    fun switchWithDateCurrentUser(context: Context, activity: Class<out AppCompatActivity>, currentUser: User, companionUser: User) {
        val intent = Intent(context, activity)
        intent.putExtra(BuildConfig.CURRENT_USER_KEY,currentUser)
        intent.putExtra(BuildConfig.COMPANION_KEY, companionUser)
        context.startActivity(intent)
    }


    fun switchWithDataFromLatestMessageActivity(context: Context, activity: Class<out AppCompatActivity>, currentUserPhotoUrl: String, companionUserId: String, companionUserName: String, companionUserPhotoUrls: String) {
        val intent = Intent(context, activity)
        intent.putExtra("1", currentUserPhotoUrl)
        intent.putExtra("2", companionUserId)
        intent.putExtra("3", companionUserName)
        intent.putExtra("4", companionUserPhotoUrls)
        context.startActivity(intent)
    }

}