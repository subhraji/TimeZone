package com.example.timezone.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.timezone.MainActivity
import com.example.timezone.R
import com.example.timezone.helper.PrefManager
import com.example.timezone.helper.transparentStatusBar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentStatusBar()
        setContentView(R.layout.activity_login)

        loginBtn.setOnClickListener {
            val username = username_tv.text
            val password = password_tv.text
            login(username.toString(),password.toString())
        }
    }

    private fun login(username:String, password:String){
        if(username.equals("timezone") && password.equals("12345")){
            PrefManager.setLogInStatus(true)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }else{
            Toast.makeText(this,"Wrong credentials !",Toast.LENGTH_SHORT).show()
        }
    }
}