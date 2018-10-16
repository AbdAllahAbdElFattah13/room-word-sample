package com.abdallah.roomwordsample.presentation_layer.words_listing_activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.abdallah.roomwordsample.R
import com.abdallah.roomwordsample.data_layer.models.Word

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlin.properties.Delegates

class WordsActivity : AppCompatActivity() {

    private var mWordsViewModel: WordViewModel by Delegates.notNull()
    private val mWordsAdapter = WordListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        setupRecyclerView()

        setupTheViewModelObservation()

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRecyclerView() {
        recyclerview.adapter = mWordsAdapter
        recyclerview.layoutManager = LinearLayoutManager(this)
    }

    private fun setupTheViewModelObservation() {
        mWordsViewModel = ViewModelProviders.of(this).get(WordViewModel::class.java)
        mWordsViewModel.getAllWords().observe(this,
            Observer<List<Word>> {
                it?.let { wordsList ->
                    mWordsAdapter.updateWords(wordsList)
                }
            })
    }
}