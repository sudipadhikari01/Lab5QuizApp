package com.sudip.lab5.quizapp

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private val quizList = ArrayList<Quiz>()

    private lateinit var radioBtnOption1: RadioButton
    private lateinit var radioBtnOption2: RadioButton
    private lateinit var radioBtnOption3: RadioButton

    private lateinit var checkboxBtnOption1: CheckBox
    private lateinit var checkboxBtnOption2: CheckBox
    private lateinit var checkboxBtnOption3: CheckBox

    private var answerFirst = ""
    private var answerSecond = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initQuizQuestionsAnswers()

        initializeViews()
    }

    private fun initializeViews() {
        radioBtnOption1 = findViewById(R.id.radio_option1_q1)
        radioBtnOption2 = findViewById(R.id.radio_option2_q1)
        radioBtnOption3 = findViewById(R.id.radio_option3_q1)

        checkboxBtnOption1 = findViewById(R.id.checkbox_option1_q2)
        checkboxBtnOption2 = findViewById(R.id.checkbox_option2_q2)
        checkboxBtnOption3 = findViewById(R.id.checkbox_option3_q2)
    }

    private fun initQuizQuestionsAnswers() {
        val quiz1 = Quiz(
            "A",
            1
        )

        val quiz2 = Quiz(
            "AB",
            2
        )

        quizList.add(0, quiz1)
        quizList.add(1, quiz2)
    }

    fun onResetClicked(view: View) {


        radioBtnOption1.isChecked = false
        radioBtnOption2.isChecked = false
        radioBtnOption3.isChecked = false


        checkboxBtnOption1.isChecked = false
        checkboxBtnOption2.isChecked = false
        checkboxBtnOption3.isChecked = false


        answerFirst = ""
        answerSecond = ""
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun onSubmitClicked(view: View) {

        var totalScore = 0

        val currentDate = LocalDateTime.now()


        val formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dateFormat = currentDate.format(formatterDate)


        val formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss")
        val time = currentDate.format(formatterTime)

        readAnswerSecond()

        if (validateAnswerFirst()) {
            totalScore += 50
        }
        if (validateAnswerSecond()) {
            totalScore += 50
        }

        val messageToDisplay =
            "Congratulations! You submitted on current Date: $dateFormat and Time: $time, You achieved $totalScore %"

        displayDialogMessage(messageToDisplay, "Your Quiz Score is here..")
    }

    private fun displayDialogMessage(message: String, title: String) {
        val builder: AlertDialog.Builder = this.let {
            AlertDialog.Builder(it)
        }

        builder.setMessage(message)!!.setTitle(title)

        val dialogBox: AlertDialog = builder.create()
        dialogBox.show()
    }

    private fun validateAnswerFirst(): Boolean {
        val quiz = quizList[0]
        if (quiz.answer == answerFirst) {
            return true
        }
        return false
    }

    private fun validateAnswerSecond(): Boolean {
        val quiz = quizList[1]
        if (quiz.answer == answerSecond) {
            return true
        }
        return false
    }

    private fun readAnswerSecond() {
        answerSecond = ""
        if (checkboxBtnOption1.isChecked) {
            answerSecond += "A"
        }
        if (checkboxBtnOption2.isChecked) {
            answerSecond += "B"
        }

        if (checkboxBtnOption3.isChecked) {
            answerSecond += "C"
        }

    }

    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {

            val checkedRadio = view.isChecked

            when (view.getId()) {
                R.id.radio_option1_q1 ->
                    if (checkedRadio) {
                        answerFirst = "A"
                    }
                R.id.radio_option2_q1 ->
                    if (checkedRadio) {
                        answerFirst = "B"
                    }

                R.id.radio_option3_q1 ->
                    if (checkedRadio) {
                        answerFirst = "C"
                    }
            }
        }
    }
}