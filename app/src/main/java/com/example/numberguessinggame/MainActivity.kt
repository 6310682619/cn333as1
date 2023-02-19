package com.example.numberguessinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import kotlin.random.Random.Default.nextInt

class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var textViewHeader: TextView
    lateinit var getNumber: EditText
    lateinit var imageButtonReplay: ImageButton
    lateinit var imageButtonEnter: ImageButton

    var random: Int = nextInt(1,1000)
    var numTries: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        getNumber = findViewById(R.id.getNumber)
        imageButtonReplay = findViewById(R.id.imageButtonReplay)
        imageButtonEnter = findViewById(R.id.imageButtonEnter)
        textViewHeader = findViewById(R.id.textViewHeader)

        textView.text = "Enter number"
        textViewHeader.text = "Try to guess the number from 1 - 1,000!"

        imageButtonEnter.setOnClickListener {
            val num: Int = getNumber.text.toString().toInt()
            if (num < random){
                if ((random - num) < 10){
                    textView.text = "So close! your number is too low."
                }
                else {
                    textView.text = "Your number is too low."
                }
                numTries++
                getNumber.text.clear()
            }
            else if(num > random){
                if ((num - random) < 10){
                    textView.text = "So close! your number is too high."
                }
                else if(num > 1000){
                    textView.text = "Your number is out of range."
                }
                else {
                    textView.text = "Your number is too high."
                }
                numTries++
                getNumber.text.clear()
            }
            else {
                textView.text = "Congratulations! you found the number in " + numTries + " attempts."
                getNumber.text.clear()
            }
        }

        imageButtonReplay.setOnClickListener {
            Replay()
        }
    }
    fun Replay(){
        random = nextInt(1,1000)
        textView.text = "Enter number"
        getNumber.text.clear()
    }
}