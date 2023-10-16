package com.example.myquizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        mQuestionsList = Constants.getQuestions()
        Log.i("Questions size", "${mQuestionsList!!.size}")

        setQuestion()

        findViewById<TextView>(R.id.tv_option_one).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_option_two).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_option_three).setOnClickListener(this)
        findViewById<TextView>(R.id.tv_option_four).setOnClickListener(this)
    }

    private fun setQuestion() {
        defaultOptionsView()

        val question: Question? = mQuestionsList!![mCurrentPosition - 1]

        findViewById<ProgressBar>(R.id.progressBar).progress = mCurrentPosition
        findViewById<TextView>(R.id.tv_progress).text = "$mCurrentPosition" + "/" + findViewById<ProgressBar>(R.id.progressBar).max

        findViewById<TextView>(R.id.tv_question).text = question!!.question
        findViewById<ImageView>(R.id.iv_image).setImageResource(question.image)

        findViewById<TextView>(R.id.tv_option_one).text = question!!.optionOne
        findViewById<TextView>(R.id.tv_option_two).text = question!!.optionTwo
        findViewById<TextView>(R.id.tv_option_three).text = question!!.optionThree
        findViewById<TextView>(R.id.tv_option_four).text = question!!.optionFour
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        options.add( findViewById<TextView>(R.id.tv_option_one) )
        options.add( findViewById<TextView>(R.id.tv_option_two) )
        options.add( findViewById<TextView>(R.id.tv_option_three) )
        options.add( findViewById<TextView>(R.id.tv_option_four) )

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this, R.drawable.default_option_border_bg
            )
        }

    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.tv_option_one -> {
                selectedOptionView(v as TextView, 1)
            }
            R.id.tv_option_two -> {
                selectedOptionView(v as TextView, 2)
            }
            R.id.tv_option_three -> {
                selectedOptionView(v as TextView, 3)
            }
            R.id.tv_option_four -> {
                selectedOptionView(v as TextView, 4)
            }
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNumber: Int) {
        defaultOptionsView()
        mSelectedOptionPosition = selectedOptionNumber

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.selected_option_border_bg
        )

    }
}