package com.example.wordsfactory

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wordsfactory.databinding.ActivityIntro1Binding
import com.example.wordsfactory.databinding.ActivityIntro2Binding
import com.example.wordsfactory.databinding.ActivityMainBinding

class Intro1Activity : AppCompatActivity() {
    lateinit var bindingClass: ActivityIntro1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityIntro1Binding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        bindingClass.btNext.setOnClickListener {
            val intent = Intent(this, Intro2Activity::class.java)
            startActivity(intent)
        }
    }
}