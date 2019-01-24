package com.example.piachimov.firebasekotlinexample.ui.newMessage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.example.piachimov.firebasekotlinexample.R
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.model.User
import kotlinx.android.synthetic.main.activity_new_message.*
import javax.inject.Inject

class NewMessageActivity : AppCompatActivity(), NewMessageView {



    @Inject
    lateinit var newMessagePresenter: NewMessagePresenter
    @Inject
    lateinit var newMessageRecyclerAdapter: NewMessageRecyclerAdapter

    var friendsUserList: ArrayList<User> = arrayListOf()
    private lateinit var currentUser1: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_message)
        Injector.initNewMessageActivityComponent(this)
        Injector.newMessagesActivityComponent?.inject(this)
        supportActionBar?.title = this.getString(R.string.select_user)
        swapVisibility()
        initRecyclerView()
        newMessagePresenter.getDataFromfirebaseDatabas()

    }

    override fun onSuccess(list: ArrayList<User>) {
        swapVisibility()
        newMessagePresenter.getCurrentUser(list)
        for (friendsUserItem in list){
            if (friendsUserItem.id != currentUser1.id){
                friendsUserList.add(friendsUserItem)
            }
        }
        newMessageRecyclerAdapter.showList(friendsUserList)
    }

    override fun onFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onGetCurrentUser(currentUser: User) {
        currentUser1 = currentUser
        Log.d("currentuser", currentUser1.username)
    }


    fun initRecyclerView() {
        new_message_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        new_message_recycler_view.adapter = newMessageRecyclerAdapter
    }


    private fun swapVisibility() {
        progress_bar.visibility = if (progress_bar.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        new_message_recycler_view.visibility = if (new_message_recycler_view.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        Injector.releaseNewMessageActivityComponent()
    }
}
