package com.example.games

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*
// EL JUEGO TRATA DE ADIVINAR ANIMALES FANTASTICOS.
class GameActivity : AppCompatActivity() {

    private lateinit var words: Array<String>
    private var random: Random? = null
    private var currWord: String? = null
    private lateinit var charViews: Array<TextView?>
    private var wordLayout: LinearLayout? = null
    private var adapter: LetterAdapter? = null
    private var gridView: GridView? = null
    private var numCorr = 0
    private var numChars = 0
    private lateinit var parts: Array<ImageView?>
    private val sizeParts = 6
    private var currPart = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        words = resources.getStringArray(R.array.words)
        wordLayout = findViewById<LinearLayout>(R.id.words)
        gridView = findViewById<GridView>(R.id.letters)
        random = Random()

        parts = arrayOfNulls(sizeParts)
        parts[0] = findViewById<ImageView>(R.id.head)
        parts[1] = findViewById<ImageView>(R.id.body)
        parts[2] = findViewById<ImageView>(R.id.arm_left)
        parts[3] = findViewById<ImageView>(R.id.arm_right)
        parts[4] = findViewById<ImageView>(R.id.leg_left)
        parts[5] = findViewById<ImageView>(R.id.leg_right)


        playGame()
    }

    private fun playGame() {
        var newWord = words[random!!.nextInt(words.size)]
        while (newWord == currWord) newWord = words[random!!.nextInt(words.size)]

        currWord = newWord

        charViews = arrayOfNulls(currWord!!.length)

        wordLayout!!.removeAllViews()

        for (i in 0 until currWord!!.length) {
            charViews[i] = TextView(this)
            charViews[i]!!.text = "" + currWord!![i]
            charViews[i]!!.layoutParams =
                ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            charViews[i]!!.setTextColor(Color.WHITE)
            charViews[i]!!.setBackgroundResource(R.drawable.letter_bg)
            wordLayout!!.addView(charViews[i])
        }
        adapter = LetterAdapter(this)
        gridView!!.adapter = adapter
        numCorr = 0
        currPart = 0
        numChars = currWord!!.length

        for (i in 0 until sizeParts) {
            parts[i]!!.visibility = View.INVISIBLE
        }
    }
    fun letterPressed(view: View) {
        val letter = (view as TextView).text.toString()
        val letterChar = letter[0]
        view.setEnabled(false)
        var correct = false
        for (i in 0 until currWord!!.length) {
            if (currWord!![i] == letterChar) {
                correct = true
                numCorr++
                charViews[i]!!.setTextColor(Color.BLACK)
            }
        }
        if (numCorr == numChars) {
            showWinDialog()
        } else if (currPart < sizeParts) {
            if (!correct) {
                parts[currPart]!!.visibility = View.VISIBLE
                currPart++
            }
        } else {
            disableButtons()
            val builder = AlertDialog.Builder(this)
            builder.setTitle("LOSE")
            builder.setMessage("YOU LOSE\n\n The correct answer is  \n\n$currWord")
            builder.setPositiveButton(
                "Play again"
            ) { dialogInterface, i -> playGame() }
            builder.setNegativeButton(
                "out"
            ) { dialogInterface, i -> finish() }
            builder.show()
        }
    }

    private fun showWinDialog() {
        disableButtons()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¡you WIN!")
        builder.setMessage("¡CONGRATULATIONS!\n\n The answer are \n\n$currWord")
        builder.setPositiveButton(
            "Play again"
        ) { dialogInterface, i -> playGame() }
        builder.setNegativeButton(
            "out"
        ) { dialogInterface, i -> finish() }
        builder.show()
    }

    private fun disableButtons() {
        for (i in 0 until gridView!!.childCount) {
            gridView!!.getChildAt(i).isEnabled = false
        }
    }
}


