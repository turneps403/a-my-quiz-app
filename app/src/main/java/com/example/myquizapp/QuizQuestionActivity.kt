package com.example.myquizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView

class QuizQuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        val questionsList = Constants.getQuestions()
        Log.i("Questions size", "${questionsList.size}")

        val curPos = 1
        val question: Question? = questionsList[curPos - 1]

        findViewById<ProgressBar>(R.id.progressBar).progress = curPos
        findViewById<TextView>(R.id.tv_progress).text = "$curPos" + "/" + findViewById<ProgressBar>(R.id.progressBar).max

        findViewById<TextView>(R.id.tv_question).text = question!!.question
        findViewById<ImageView>(R.id.iv_image).setImageResource(question.image)

        findViewById<TextView>(R.id.tv_option_one).text = question!!.optionOne
        findViewById<TextView>(R.id.tv_option_two).text = question!!.optionTwo
        findViewById<TextView>(R.id.tv_option_three).text = question!!.optionThree
        findViewById<TextView>(R.id.tv_option_four).text = question!!.optionFour
    }
}