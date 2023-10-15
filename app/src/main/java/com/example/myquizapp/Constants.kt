package com.example.myquizapp

object Constants {
    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()

        // 1
        val q1 = Question(
            1,
            "what country does this flag belong to?",
            R.drawable.flag_mn,
            "Belgia",
            "Mongolia",
            "Burjatia",
            "Japan",
            1
        )
        questionList.add(q1)

        // 2
        val q2 = Question(
            1,
            "what country does this flag belong to?",
            R.drawable.flag_br,
            "Scotland",
            "Uganda",
            "Brazil",
            "Britan",
            1
        )
        questionList.add(q2)

        return questionList
    }
}