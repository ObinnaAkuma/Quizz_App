package o.akuma.quizz

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import androidx.core.content.ContextCompat
import o.akuma.quizz.databinding.ActivityQuizQuestionsBinding
import java.util.Locale

class QuizQuestionsActivity : AppCompatActivity(), OnClickListener, TextToSpeech.OnInitListener {

    private lateinit var binding: ActivityQuizQuestionsBinding
    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Questions>? = null
    private var mSelectedOptionPosition: Int = 0

    private var tts: TextToSpeech? = null

    private var mUserName: String? = null
    private var mCorrectAnswers: Int = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizQuestionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mQuestionsList = Constants.getQuestions()

        tts = TextToSpeech(this, this)
        mUserName = intent.getStringExtra(Constants.USER_NAME)

        binding.tvOptionOne.setOnClickListener(this)
        binding.tvOptionTwo.setOnClickListener(this)
        binding.tvOptionThree.setOnClickListener(this)
        binding.tvOptionFour.setOnClickListener(this)
        binding.btnSubmit.setOnClickListener(this)

        setQuestion()


    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion() {

        defaultOptionsView()

        val question: Questions = mQuestionsList!![mCurrentPosition - 1]

        binding.ivImage.setImageResource(question.image)
        binding.progressBar.progress = mCurrentPosition
        binding.tvProgress.text = "$mCurrentPosition/${binding.progressBar.max}"
        binding.tvQuestion.text = question.question
        binding.tvOptionOne.text = question.optionOne
        binding.tvOptionTwo.text = question.optionTwo
        binding.tvOptionThree.text = question.optionThree
        binding.tvOptionFour.text = question.optionFour

        if (mCurrentPosition == mQuestionsList!!.size) {
            binding.btnSubmit.text = "FINISH"
        } else {
            binding.btnSubmit.text = "SUBMIT"
        }
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        binding.tvOptionOne.let {
            options.add(0, it)
        }

        binding.tvOptionTwo.let {
            options.add(1, it)
        }

        binding.tvOptionThree.let {
            options.add(2, it)
        }

        binding.tvOptionFour.let {
            options.add(3, it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this, R.drawable.default_options_boarder_bg
            )
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int) {
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.typeface = Typeface.DEFAULT_BOLD
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.selected_option_border_bg
        )

    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {

        when (v?.id) {
            R.id.tvOptionOne -> {
                selectedOptionView(binding.tvOptionOne, 1)
                speakOut(binding.tvOptionOne.text.toString())
            }

            R.id.tvOptionTwo -> {
                selectedOptionView(binding.tvOptionTwo, 2)
                speakOut(binding.tvOptionTwo.text.toString())
            }

            R.id.tvOptionThree -> {
                selectedOptionView(binding.tvOptionThree, 3)
                speakOut(binding.tvOptionThree.text.toString())
            }

            R.id.tvOptionFour -> {
                selectedOptionView(binding.tvOptionFour, 4)
                speakOut(binding.tvOptionFour.text.toString())
            }

            R.id.btn_Submit -> {
                if (mSelectedOptionPosition == 0) {

                    mCurrentPosition++

                    when {
                        mCurrentPosition <= mQuestionsList!!.size -> {
                            setQuestion()
                        }
                        else -> {
                            val intent = Intent(this, ResultActivity::class.java)
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
                            startActivity(intent)
                            finish()

                        }
                    }

                } else {
                    val question = mQuestionsList?.get(mCurrentPosition - 1)
                    if (question!!.correctAnswer != mSelectedOptionPosition) {
                        answerView(mSelectedOptionPosition, R.drawable.wrong_options_boarder_bg)
                    }else{
                        mCorrectAnswers++
                    }
                    answerView(question.correctAnswer, R.drawable.correct_options_boarder_bg)

                    if (mCurrentPosition == mQuestionsList!!.size) {
                        binding.btnSubmit.text = "FINISH"
                    } else {
                        binding.btnSubmit.text = "NEXT QUESTION"
                    }

                    mSelectedOptionPosition = 0
                }

            }

        }

    }

    private fun answerView(answer: Int, drawableView: Int) {
        when (answer) {
            1 -> {
                binding.tvOptionOne.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }

            2 -> {
                binding.tvOptionTwo.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }

            3 -> {
                binding.tvOptionThree.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }

            4 -> {
                binding.tvOptionFour.background = ContextCompat.getDrawable(
                    this, drawableView
                )
            }
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.ENGLISH)

            if (result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS", "Language Not Supported")
            }else{
                Log.e("TTS","Initialization Failed")
            }
        }
    }

    private fun speakOut(text: String){
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }
    override fun onDestroy() {
        super.onDestroy()
        if (tts != null) {
            tts?.stop()
            tts?.shutdown()
        }

    }


}

