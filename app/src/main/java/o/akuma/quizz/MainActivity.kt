package o.akuma.quizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import o.akuma.quizz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener {
            if (binding.etName.text?.isEmpty() == true){
                Toast.makeText(this,
                    "Please enter your name", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, QuizQuestionsActivity::class.java)
                startActivity(intent)
                //finish() ensures that the activity we leave to the next ends and doesnt stay running in the background.
                finish()
            }
        }
    }
}