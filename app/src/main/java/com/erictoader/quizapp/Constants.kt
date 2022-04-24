package com.erictoader.quizapp

object Constants {
    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_questions"
    const val CORRECT_ANSWERS: String = "correct_answers"

    fun getQuestions():ArrayList<Question> {
        val genericQuestion = "Which car brand does this logo represent?"
        val questionList = ArrayList<Question>()

        val q1 = Question(1, genericQuestion, R.drawable.ic_logo_bmw, arrayListOf("BMW", "Renault", "Ferrari", "Rolls Royce"), 0)
        val q2 = Question(2, genericQuestion, R.drawable.ic_logo_ferrari, arrayListOf("Maybach", "Ferrari", "Honda", "Porsche"), 1)
        val q3 = Question(3, genericQuestion, R.drawable.ic_logo_hyundai, arrayListOf("Honda", "Hyundai", "Tesla", "Dodge"), 1)
        val q4 = Question(4, genericQuestion, R.drawable.ic_logo_opel, arrayListOf("Lincoln", "Peugeot", "Lamborghini", "Opel"), 3)
        val q5 = Question(5, genericQuestion, R.drawable.ic_logo_peugeot, arrayListOf("Pagani", "Pegasus", "Peugeot", "Porsche"), 2)
        val q6 = Question(6, genericQuestion, R.drawable.ic_logo_seaat, arrayListOf("Seat", "Chevrolet", "Mercedes-Benz", "Bentley"), 0)
        val q7 = Question(7, genericQuestion, R.drawable.ic_logo_renault, arrayListOf("Honda", "Renault", "Chevrolet", "Tesla"), 1)
        val q8 = Question(8, genericQuestion, R.drawable.ic_logo_mercedes, arrayListOf("Maybach", "Pagani", "Mercedes-Benz", "Porsche"), 2)
        val q9 = Question(9, genericQuestion, R.drawable.ic_logo_volkswagen, arrayListOf("Peugeot", "Hyundai", "Dodge", "Volkswagen"), 3)

        questionList.add(q1)
        questionList.add(q2)
        questionList.add(q3)
        questionList.add(q4)
        questionList.add(q5)
        questionList.add(q6)
        questionList.add(q7)
        questionList.add(q8)
        questionList.add(q9)

        return questionList
    }
}