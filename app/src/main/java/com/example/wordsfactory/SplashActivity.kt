package com.example.wordsfactory

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wordsfactory.databinding.ActivityMainBinding
import com.example.wordsfactory.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingClass = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)

        Handler(Looper.getMainLooper()).postDelayed({

            val intent = Intent(this, Intro1Activity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }

}