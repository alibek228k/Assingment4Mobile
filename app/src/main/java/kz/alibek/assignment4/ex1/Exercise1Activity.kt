package kz.alibek.assignment4.ex1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kz.alibek.assignment4.R
import kz.alibek.assignment4.ex1.model.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class Exercise1Activity : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context): Intent =
            Intent(context, Exercise1Activity::class.java)
    }

    private val userViewModel: UserViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exercise1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        val adapter = UserAdapter(
            onUpdateClick = { user ->
                userViewModel.update(user)
            },
            onDeleteClick = { user ->
                userViewModel.delete(user)
            }
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val nameInput = findViewById<EditText>(R.id.name_input)
        val emailInput = findViewById<EditText>(R.id.email_input)
        val addButton = findViewById<Button>(R.id.add_button)

        addButton.setOnClickListener {
            val name = nameInput.text.toString()
            val email = emailInput.text.toString()

            if (name.isNotBlank() && email.isNotBlank()) {
                val newUser = User(name = name, email = email)
                userViewModel.insert(newUser)

                nameInput.text.clear()
                emailInput.text.clear()
            } else {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        userViewModel.allUsers.observe(this) { users ->
            users?.let { adapter.submitList(it) }
        }
    }
}
