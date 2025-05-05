package com.example.wordsfactory

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wordsfactory.databinding.ActivityIntro2Binding
import com.example.wordsfactory.databinding.ActivityIntro3Binding

class Intro3Activity : AppCompatActivity() {

    lateinit var bindingClass: ActivityIntro3Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivityIntro3Binding.inflate(layoutInflater)
        setContentView(bindingClass.root)

    }
}