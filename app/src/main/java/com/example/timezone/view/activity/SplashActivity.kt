package com.example.timezone.view.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.timezone.MainActivity
import com.example.timezone.R
import com.example.timezone.helper.PrefManager
import com.example.timezone.helper.transparentStatusBar

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentStatusBar()
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            if(PrefManager.getLogInStatus() == true){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

        }, 1000)
    }
}