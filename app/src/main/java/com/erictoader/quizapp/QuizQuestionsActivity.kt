package com.erictoader.quizapp

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {
    private var userName : String? = null
    private var tvQuestion : TextView? = null
    private var ivLogo : ImageView? = null
    private var progressBar : ProgressBar? = null
    private var progressNum : TextView? = null
    private var option1 : TextView? = null
    private var option2 : TextView? = null
    private var option3 : TextView? = null
    private var option4 : TextView? = null
    private var submitButton : Button? = null

    private var currentQuestionIndex = 0
    private var totalQuestionsCount = 0
    private var questionsList : ArrayList<Question>? = null
    private var currentQuestion : Question? = null

    private var selectedOption = -1
    private var submitted = false
    var correctAnswers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        userName = intent.getStringExtra(Constants.USER_NAME)

        tvQuestion = findViewById(R.id.tvQuestion)
        ivLogo = findViewById(R.id.ivLogo)
        progressBar = findViewById(R.id.progressBar)
        progressNum = findViewById(R.id.tvQuestionID)
        option1 = findViewById(R.id.tvOption1)
        option2 = findViewById(R.id.tvOption2)
        option3 = findViewById(R.id.tvOption3)
        option4 = findViewById(R.id.tvOption4)
        submitButton = findViewById(R.id.submitbutton)

        option1?.setOnClickListener(this)
        option2?.setOnClickListener(this)
        option3?.setOnClickListener(this)
        option4?.setOnClickListener(this)

        questionsList = Constants.getQuestions()
        currentQuestion = questionsList!![currentQuestionIndex]
        totalQuestionsCount = questionsList!!.size

        loadQuestion()
        defaultOptionsView()

        submitButton?.setOnClickListener {
            if(!submitted) {
                if (selectedOption != -1) {
                    val options = ArrayList<TextView>()
                    option1?.let { options.add(it) }
                    option2?.let { options.add(it) }
                    option3?.let { options.add(it) }
                    option4?.let { options.add(it) }
                    if (selectedOption == currentQuestion!!.correct) {
                        options[selectedOption].setBackgroundResource(R.drawable.correct_option_border_bg)
                        correctAnswers++
                    } else {
                        options[selectedOption].setBackgroundResource(R.drawable.wrong_option_border_bg)
                        options[currentQuestion!!.correct].setBackgroundResource(R.drawable.correct_option_border_bg)
                        options[currentQuestion!!.correct].setTextColor(resources.getColor(R.color.black, null))
                        options[currentQuestion!!.correct].setTypeface(options[currentQuestion!!.correct].typeface, Typeface.BOLD)
                    }
                    didSubmit()
                } else {
                    Toast.makeText(this, "Select an option before submitting", Toast.LENGTH_SHORT).show()
                }
            } else {
                currentQuestionIndex++
                if(currentQuestionIndex != questionsList!!.size) {
                    currentQuestion = questionsList!![currentQuestionIndex]
                    selectedOption = -1
                    didNextQuestion()
                    loadQuestion()
                    defaultOptionsView()
                } else {
                    val intent = Intent(this, ResultActivity::class.java)
                    intent.putExtra(Constants.USER_NAME, userName)
                    intent.putExtra(Constants.TOTAL_QUESTIONS, totalQuestionsCount)
                    intent.putExtra(Constants.CORRECT_ANSWERS, correctAnswers)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun didSubmit() {
        submitted = true
        when {
            currentQuestionIndex < 8 -> {
                submitButton?.text = "GO TO NEXT QUESTION"
            }
            else -> {
                submitButton?.text = "FINISH QUIZ"
            }
        }
    }

    private fun didNextQuestion() {
        submitted = false
        submitButton?.text = "SUBMIT"
    }

    private fun loadQuestion() {
        tvQuestion?.text = currentQuestion?.question
        currentQuestion?.image?.let { ivLogo?.setImageResource(it) }
        progressBar?.progress = currentQuestionIndex + 1
        progressNum?.text = "${currentQuestionIndex + 1}/$totalQuestionsCount"
        option1?.text = currentQuestion?.options?.get(0)
        option2?.text = currentQuestion?.options?.get(1)
        option3?.text = currentQuestion?.options?.get(2)
        option4?.text = currentQuestion?.options?.get(3)
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        option1?.let { options.add(it) }
        option2?.let { options.add(it) }
        option3?.let { options.add(it) }
        option4?.let { options.add(it) }

        for(option in options) {
            option.setBackgroundResource(R.drawable.default_option_border_bg)
            option.setTextColor(resources.getColor(R.color.default_option_color_text, null))
            option.typeface = Typeface.DEFAULT
        }
    }

    private fun selectOption(tv: TextView, selectedOptionIndex: Int) {
        if(!submitted) {
            defaultOptionsView()
            selectedOption = selectedOptionIndex

            tv.setBackgroundResource(R.drawable.selected_option_border_bg)
            tv.setTextColor(resources.getColor(R.color.black, null))
            tv.setTypeface(tv.typeface, Typeface.BOLD)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.tvOption1 -> {
                option1?.let {
                    selectOption(it, 0)
                }
            }
            R.id.tvOption2 -> {
                option2?.let {
                    selectOption(it, 1)
                }
            }
            R.id.tvOption3 -> {
                option3?.let {
                    selectOption(it, 2)
                }
            }
            R.id.tvOption4 -> {
                option4?.let {
                    selectOption(it, 3)
                }
            }
        }
    }
}