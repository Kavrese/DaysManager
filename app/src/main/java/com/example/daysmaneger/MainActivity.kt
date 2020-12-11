package com.example.daysmaneger

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.layout_content.*
import kotlinx.android.synthetic.main.lin_add_tasks.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onStartScreen()

        img_avatar.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        val data = mutableListOf<ModelTask>()
        data.add(ModelTask("name", "00:05", true))
        data.add(ModelTask("name", "time", false))
        data.add(ModelTask("name", "time", false))
        data.add(ModelTask("name", "time", false))
        data.add(ModelTask("name", "time", false))
        data.add(ModelTask("name", "time", false))
        data.add(ModelTask("name", "00:05", true))
        data.add(ModelTask("name", "time", false))
        data.add(ModelTask("name", "time", false))
        data.add(ModelTask("name", "time", false))
        data.add(ModelTask("name", "time", false))
        data.add(ModelTask("name", "time", false))
        data.add(ModelTask("name", "00:05", true))
        data.add(ModelTask("name", "time", false))

        rec_content.apply {
            adapter = AdapterItems(data)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        upper_view.setOnClickListener {
            motion_created.transitionToStart()
        }
        add_items.setOnClickListener {
            motion_created.transitionToEnd()
        }
        save.setOnClickListener {
            motion_created.transitionToStart()
            data.add(ModelTask(edit_text_name.text.toString(), "time", false))
            rec_content.adapter?.notifyDataSetChanged()
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