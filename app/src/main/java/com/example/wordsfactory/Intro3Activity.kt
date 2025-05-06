package com.example.wordsfactory

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wordsfactory.databinding.ActivityIntro1Binding
import com.example.wordsfactory.databinding.ActivityIntro2Binding
import com.example.wordsfactory.databinding.ActivityIntro3Binding

class Intro3Activity : AppCompatActivity() {

    lateinit var bindingClass: ActivityIntro3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityIntro3Binding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        bindingClass.btNext.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

        bindingClass.btSkip.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}