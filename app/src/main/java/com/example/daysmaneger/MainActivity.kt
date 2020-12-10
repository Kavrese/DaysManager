package com.example.daysmaneger

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onStartScreen()

        img_avatar.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    private fun onStartScreen(){
        val motionStart = findViewById<MotionLayout>(R.id.motion_start)
        motionStart.visibility = View.VISIBLE
        //Start Screen
        Handler().postDelayed(Runnable {
            //Врубаем анимацию
            motionStart.transitionToEnd()
            //Таймер окончания анимации
            Handler().postDelayed(Runnable {
                //Делаем screen не активным
                motionStart.visibility = View.GONE
            }, 600)
        }, 1500)
    }
}