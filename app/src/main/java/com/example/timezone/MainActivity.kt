package com.example.timezone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.timezone.helper.transparentStatusBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_current_time.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentStatusBar()
        setContentView(R.layout.activity_main)

    }

}