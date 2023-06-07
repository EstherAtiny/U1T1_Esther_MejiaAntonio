package com.example.games

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

// basado en el video de https://www.youtube.com/watch?v=l4c4IShM0Wo
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun play(view: View) {
        val objInt: Intent = Intent(view.context, GameActivity::class.java)
        startActivity(objInt)
    }

}