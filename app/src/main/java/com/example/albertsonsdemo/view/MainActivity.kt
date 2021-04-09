package com.example.albertsonsdemo.view

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.albertsonsdemo.R
import com.example.albertsonsdemo.adapter.AcronymRVAdapter
import com.example.albertsonsdemo.databinding.ActivityMainBinding
import com.example.albertsonsdemo.model.AcronymItem
import com.example.albertsonsdemo.model.AcronymWrapper
import com.example.albertsonsdemo.viewmodel.AcronymViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: AcronymViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvDefinitions.layoutManager = linearLayoutManager

        setUpListeners()
        setUpObservers()
    }

    private fun setUpListeners() {
        binding.btnFetch.setOnClickListener {
            getLongForm()
        }

        binding.etAcronym.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                getLongForm()
                true
            } else {
                false
            }
        })
    }

    private fun getLongForm() {
        viewModel.fetchAcronym(binding.etAcronym.text.toString())
    }

    private fun setUpObservers() {
        viewModel.acronym.observe(this, Observer<AcronymWrapper> {
            val httpCode = it.httpCode
            if (httpCode == 200) {
                binding.tvError.visibility = View.GONE
                loadRV(it.acronym)
            } else {
                errorDisplay(it.httpCode, it.message)
            }
        })
    }

    private fun errorDisplay(httpCode: Int, message: String) {
        val strFormat = getString(R.string.restError)

        binding.tvError.text = String.format(strFormat, httpCode, message)
        binding.tvError.visibility = View.VISIBLE
    }

    private fun loadRV(acronym: List<AcronymItem>?) {
        acronym?.let {
            binding.rvDefinitions.adapter = AcronymRVAdapter(it)
        }
    }

}