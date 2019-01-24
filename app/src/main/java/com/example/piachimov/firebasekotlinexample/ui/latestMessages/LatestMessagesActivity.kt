package com.example.piachimov.firebasekotlinexample.ui.latestMessages

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import com.example.piachimov.firebasekotlinexample.R
import com.example.piachimov.firebasekotlinexample.di.Injector
import com.example.piachimov.firebasekotlinexample.model.Message
import com.example.piachimov.firebasekotlinexample.ui.newMessage.NewMessageActivity
import com.example.piachimov.firebasekotlinexample.utils.ScreenNavigation
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_latest_message.*
import javax.inject.Inject

class LatestMessagesActivity : AppCompatActivity(), LatestMessagesView {

    @Inject
    lateinit var latestMessagesPresenter: LatestMessagesPresenter
    @Inject
    lateinit var latestMessageRecyclerAdapter: LatestMessageRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_latest_message)
        Injector.initLatestMessagesActivityComponent(this)
        Injector.latestMessagesActivityComponent?.inject(this)
        initRecyclerView()
        latestMessagesPresenter.listenForLatestMessages()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.menu_logout ->{
            latestMessagesPresenter.logOut()
            }
            R.id.menu_new_message ->{
            ScreenNavigation.switchActivity(this, NewMessageActivity::class.java)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun initRecyclerView() {
        latest_message_recycler_view.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        latest_message_recycler_view.adapter = latestMessageRecyclerAdapter
    }


    override fun onSuccess(messageList: ArrayList<Message>) {
        latestMessageRecyclerAdapter.showLatestMessagesList(messageList)
    }


    override fun onFailure(message: String) {

    }


    override fun onDestroy() {
        super.onDestroy()
        Injector.releaseLatestMessageActivityComponent()
    }
}