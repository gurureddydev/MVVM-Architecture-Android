package com.gurureddy.mvvm_architecture_android.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gurureddy.mvvm_architecture_android.MVVMApplication
import com.gurureddy.mvvm_architecture_android.R
import com.gurureddy.mvvm_architecture_android.data.model.Article
import com.gurureddy.mvvm_architecture_android.databinding.ActivityMainBinding
import com.gurureddy.mvvm_architecture_android.di.component.DaggerActivityComponent
import com.gurureddy.mvvm_architecture_android.di.module.ActivityModule
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var adapter: TopHeadlineAdapter

    @Inject
    lateinit var topHeadlineViewModel: TopHeadlineViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setUpUI()
        setUpObserver()

    }

    private fun setUpUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                topHeadlineViewModel.uiState.collect { article ->
                    when (article) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(article.data)
                            binding.recyclerView.visibility = View.VISIBLE
                        }
                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }
                        is UiState.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@MainActivity, article.message, Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(articleList: List<Article>) {
        adapter.addData(articleList)
        adapter.notifyDataSetChanged()
    }

    private fun injectDependencies() {

        DaggerActivityComponent.builder()
            .applicationComponent((application as MVVMApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)

    }
}
