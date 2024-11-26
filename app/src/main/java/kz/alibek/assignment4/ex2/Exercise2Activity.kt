package kz.alibek.assignment4.ex2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kz.alibek.assignment4.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class Exercise2Activity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent =
            Intent(context, Exercise2Activity::class.java)
    }

    private val viewModel: ItemViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exercise2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val progressBar = findViewById<ProgressBar>(R.id.progress_bar)
        val adapter = PostAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.items.collectLatest { items ->
                adapter.submitList(items)
            }

            viewModel.error.collectLatest { error ->
                error?.let {
                    Toast.makeText(this@Exercise2Activity, it, Toast.LENGTH_SHORT).show()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.isLoading.collectLatest { isLoading ->
                if (isLoading) {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                } else {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                }
            }
        }

        viewModel.loadItems()
    }
}