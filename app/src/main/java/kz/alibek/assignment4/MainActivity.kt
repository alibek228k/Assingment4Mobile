package kz.alibek.assignment4

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kz.alibek.assignment4.ex1.Exercise1Activity
import kz.alibek.assignment4.ex2.Exercise2Activity

class MainActivity : AppCompatActivity() {

    private var ex1Button: Button? = null
    private var ex2Button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ex1Button = findViewById(R.id.ex1_button)
        ex2Button = findViewById(R.id.ex2_button)

        ex1Button?.setOnClickListener {
            startActivity(Exercise1Activity.newIntent(this))
        }

        ex2Button?.setOnClickListener {
            startActivity(Exercise2Activity.newIntent(this))
        }
    }
}