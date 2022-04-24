package com.erictoader.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val startButton : Button = findViewById(R.id.startButton)
        val nameText : TextView = findViewById(R.id.nameText)

        startButton.setOnClickListener {
            if(nameText.text.isNotEmpty()) {
                val intent : Intent = Intent(this, QuizQuestionsActivity::class.java)
                intent.putExtra(Constants.USER_NAME, nameText.text.toString())
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please enter your name to continue", Toast.LENGTH_SHORT).show()
            }
        }
    }


}