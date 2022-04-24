package com.erictoader.quizapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {
    private var userName : String? = null
    private var totalQ : Int? = null
    private var correctA : Int? = null

    private var tvCongratulations : TextView? = null
    private var tvScore : TextView? = null
    private var btnFinish : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        userName = intent.getStringExtra(Constants.USER_NAME)
        totalQ = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        correctA = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        tvCongratulations = findViewById(R.id.tvCongratulations)
        tvScore = findViewById(R.id.tvScore)
        btnFinish = findViewById(R.id.btnFinish)

        tvCongratulations?.text = "Congratulations, $userName!"
        tvScore?.text = "Your score is $correctA out of $totalQ."

        btnFinish?.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}