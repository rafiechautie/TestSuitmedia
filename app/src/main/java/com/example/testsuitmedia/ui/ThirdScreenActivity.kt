package com.example.testsuitmedia.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testsuitmedia.R
import com.example.testsuitmedia.databinding.ActivitySecondScreenBinding
import com.example.testsuitmedia.databinding.ActivityThirdScreenBinding
import com.example.testsuitmedia.ui.adapter.LoadingStateAdapter
import com.example.testsuitmedia.ui.adapter.UsersAdapter
import com.example.testsuitmedia.ui.viewmodel.UserViewModel

class ThirdScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding
    private lateinit var swipe: SwipeRefreshLayout
    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val secondBar = binding.thirdToolbar

        secondBar.setNavigationOnClickListener {
            onBackPressed()
        }


        binding.rvUser.layoutManager = LinearLayoutManager(this@ThirdScreenActivity)
        getData()
        swipe = binding.swipeRefreshLayout
        swipe.setOnRefreshListener {
            getData()
            swipe.isRefreshing = false
        }
    }


    private fun getData() {
        val adapter = UsersAdapter()
        binding.rvUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        adapter.itemCount < 1
        viewModel.users.observe(this){
            if (it == null) {
                binding.ivUserEmpty.visibility = View.VISIBLE
                binding.tvUserEmpty.visibility = View.VISIBLE
                Toast.makeText(this@ThirdScreenActivity, "User not found!", Toast.LENGTH_LONG).show()
            }else{
                binding.ivUserEmpty.visibility = View.INVISIBLE
                binding.tvUserEmpty.visibility = View.INVISIBLE
                adapter.submitData(lifecycle, it)
            }
        }


    }
}