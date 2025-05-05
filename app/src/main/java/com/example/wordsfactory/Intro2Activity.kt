package com.example.wordsfactory

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wordsfactory.databinding.ActivityIntro2Binding

class Intro2Activity : AppCompatActivity() {
    lateinit var bindingClass: ActivityIntro2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityIntro2Binding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        bindingClass.btNext.setOnClickListener {
            intent = Intent(this, Intro3Activity::class.java)
            startActivity(intent)
        }
    }
}