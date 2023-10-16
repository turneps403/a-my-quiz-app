package com.example.myquizapp

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.content.ContextCompat

class QuizQuestionActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0
    private var mCorrectAnswers: Int = 0

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

        findViewById<Button>(R.id.btn_submit).setOnClickListener(this)
    }

    private fun setQuestion() {
        defaultOptionsView()

        if (mCurrentPosition == mQuestionsList!!.size) {
            findViewById<Button>(R.id.btn_submit).text = "FINISH"
        } else {
            findViewById<Button>(R.id.btn_submit).text = "SUBMIT"
        }

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
            R.id.btn_submit -> {
                if (mSelectedOptionPosition == 0) {
                    mCurrentPosition++
                    when{
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        } else -> {
                            Toast.makeText(this,
                                "You have completed successfully the Quiz", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_option_border_bg)
                    } else {
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        findViewById<Button>(R.id.btn_submit).text = "FINISH"
                    } else {
                        findViewById<Button>(R.id.btn_submit).text = "GO TO NEXT QUESTION"
                    }
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView: Int) {
        when(answer) {
            1 -> {
                findViewById<TextView>(R.id.tv_option_one).background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            2 -> {
                findViewById<TextView>(R.id.tv_option_two).background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            3 -> {
                findViewById<TextView>(R.id.tv_option_three).background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
            4 -> {
                findViewById<TextView>(R.id.tv_option_four).background = ContextCompat.getDrawable(
                    this, drawableView
                )
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