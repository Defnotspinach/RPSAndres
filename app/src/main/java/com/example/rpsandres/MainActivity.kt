package com.example.rpsandres

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var imgComp: ImageView
    private lateinit var imgHuman: ImageView
    private lateinit var btnRock: ImageButton
    private lateinit var btnPaper: ImageButton
    private lateinit var btnScissor: ImageButton
    private lateinit var txtCompScore: TextView
    private lateinit var txtHumanScore: TextView
    private lateinit var txtvWon: TextView

    private var compScore = 0
    private var humanScore = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgComp = findViewById(R.id.imgvComputer)
        imgHuman = findViewById(R.id.imgvHuman)
        btnRock = findViewById(R.id.imgbtnRock)
        btnPaper = findViewById(R.id.imgbtnPaper)
        btnScissor = findViewById(R.id.imgbtnSci)
        txtCompScore = findViewById(R.id.CompScore)
        txtHumanScore = findViewById(R.id.HumScore)
        txtvWon = findViewById(R.id.txtvWon)

        findViewById<Button>(R.id.resetbtn).setOnClickListener {
            compScore = 0
            humanScore = 0
            updateScores()
            imgComp.setImageResource(R.drawable.cleverbaby)
            imgHuman.setImageResource(R.drawable.cleverbaby)
        }

        findViewById<Button>(R.id.closebtn).setOnClickListener {
            finish()
        }

        btnRock.setOnClickListener { playTurn("rock") }
        btnPaper.setOnClickListener { playTurn("paper") }
        btnScissor.setOnClickListener { playTurn("scissor") }
    }

    private fun playTurn(playerChoice: String) {
        val choices = listOf("rock", "paper", "scissor")
        val compChoice = choices[Random.nextInt(3)]

        updateImages(playerChoice, compChoice)

        when {
            playerChoice == compChoice -> {
                Toast.makeText(this, "It's a Draw!", Toast.LENGTH_SHORT).show()
            }
            (playerChoice == "rock" && compChoice == "scissor") ||
                    (playerChoice == "paper" && compChoice == "rock") ||
                    (playerChoice == "scissor" && compChoice == "paper") -> {
                humanScore++
                Toast.makeText(this, "You Win!", Toast.LENGTH_SHORT).show()
            }
            else -> {
                compScore++
                Toast.makeText(this, "Computer Wins!", Toast.LENGTH_SHORT).show()
            }
        }

        updateScores()
    }

    private fun updateImages(player: String, comp: String) {
        imgHuman.setImageResource(getDrawableId(player, true))
        imgComp.setImageResource(getDrawableId(comp, false))
    }

    private fun getDrawableId(choice: String, isHuman: Boolean): Int {
        return when (choice) {
            "rock" -> if (isHuman) R.drawable.rock_right else R.drawable.rock_left
            "paper" -> if (isHuman) R.drawable.paper_right else R.drawable.paper_left
            "scissor" -> if (isHuman) R.drawable.scissor_right else R.drawable.scissor_left
            else -> R.drawable.ic_launcher_foreground
        }
    }

    private fun updateScores() {
        txtCompScore.text = compScore.toString()
        txtHumanScore.text = humanScore.toString()

        if (humanScore == 5) {
            txtvWon.text = "Congratulation you win!"
        } else if (compScore == 5) {
            txtvWon.text = "Sorry try again next time!"
        } else {
            txtvWon.text = ""
        }
    }
}
