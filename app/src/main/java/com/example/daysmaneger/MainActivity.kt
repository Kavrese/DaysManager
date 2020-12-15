package com.example.daysmaneger

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_content.*
import kotlinx.android.synthetic.main.lin_add_tasks.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        onStartScreen()

        img_avatar.setOnClickListener{
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        var data = initList()
        rec_content.apply {
            adapter = AdapterItems(getDayTasks(data, "15", "12", "2020"))
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        //Закрытие окна
        upper_view.setOnClickListener {
            motion_created.transitionToStart()
        }
        //Открытие окна
        add_items.setOnClickListener {
            motion_created.transitionToEnd()
        }
        //Сохраненние Задачи
        save.setOnClickListener {
            //Закрываем окно
            motion_created.transitionToStart()

            val date_text_day = date_text.text.toString().substring(0, 2)
            val date_text_month = date_text.text.toString().substring(3, 5)
            val date_text_year = date_text.text.toString().substring(6, 10)
            //Добавляем в лист новый таск через метод addNewTasks()
            data = addNewTasks(data, edit_text_name.text.toString(), date_text_day, date_text_month, date_text_year, text_op.text.toString(), time_text.text.toString())
            rec_content.apply {
                adapter = AdapterItems(getDayTasks(data, date_text_day, date_text_month, date_text_year))
            }
        }
        date_text.setOnClickListener{
            callDatePicker()
        }
        time_text.setOnClickListener{
            callTimePicker()
        }
    }

    private fun addNewTasks(list_days: MutableList<ModelDay>, name_tasks: String, day: String, month: String, year: String, op: String, time_end: String): MutableList<ModelDay> {
        var find_day = 0
        var find_day_bool = false
        for (i in 0..list_days.size - 1){
            if (list_days[i].day == day && list_days[i].month == month && list_days[i].years == year){
                find_day = i
                find_day_bool = true
            }
        }

        if (find_day_bool){
            list_days[find_day].list_tasks.add(ModelTask(name_tasks, time_end, op, false))
        }else{
            list_days.add(ModelDay(day, month, year, mutableListOf(
                ModelTask(name_tasks, time_end.substringBefore(':') + ":00", op, true),
                ModelTask(name_tasks, time_end, op, false)
            )))
        }

        return list_days
    }

    private fun initList (): MutableList<ModelDay> {
        val data = mutableListOf<ModelDay>()
        data.add(ModelDay("00", "00", "0000", mutableListOf(
            ModelTask("time1", "00:05", "",true),
            ModelTask("name1", "00:05", "",false),
            ModelTask("name2", "00:10", "",false),
            ModelTask("name3", "00:15", "",false),
            ModelTask("name4", "00:20", "",false),
            ModelTask("time2", "01:05", "",true),
            ModelTask("name5", "00:05", "",false),
            ModelTask("name6", "00:10", "",false),
            ModelTask("name7", "00:15", "",false),
            ModelTask("name8", "00:20", "",false)
        )))
        data.add(ModelDay("01", "01", "0001", mutableListOf<ModelTask>(
            ModelTask("time1", "00:05", "",true),
            ModelTask("name11", "00:05", "",false),
            ModelTask("name22", "00:10", "",false),
            ModelTask("name33", "00:15", "",false),
            ModelTask("name44", "00:20", "",false),
            ModelTask("time2", "01:05", "",true),
            ModelTask("name55", "00:05", "",false),
            ModelTask("name66", "00:10", "",false),
            ModelTask("name77", "00:15", "",false),
            ModelTask("name88", "00:20", "",false)
        )))
        return data
    }

    private fun getDayTasks(list_days: MutableList<ModelDay>, day:String, month:String, year: String): List<ModelTask> {
        var returnTasks: List<ModelTask> = mutableListOf()
        for (i in 0..(list_days.size - 1)){
            if (list_days[i].day == day && list_days[i].month == month && list_days[i].years == year){
                returnTasks = list_days[i].list_tasks
            }
        }
        return returnTasks
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

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.time -> callTimePicker()
            R.id.date -> callDatePicker()
        }
    }

    private fun callTimePicker(){
        val hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val minute = Calendar.getInstance().get(Calendar.MINUTE)

        TimePickerDialog(this,
        TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
            time_text.text = "${if (i < 10) { "0$i" } else {i.toString()}}:${if (i2 < 10) { "0$i2" } else {i2.toString()}}"
        }, hours, minute, true).show()
    }

    private fun callDatePicker(){
        val years = Calendar.getInstance().get(Calendar.YEAR)
        val months = Calendar.getInstance().get(Calendar.MONTH)
        val days = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                val day = if (i3 < 10) { "0$i3" } else {i3.toString()}
                val month: String = if (i2 < 10) { "0${i2 + 1}" } else {(i2 + 1).toString()}
                val year = if (i < 10) { "0$i" } else {i.toString()}

                date_text.text = "$day.$month.$year"
            }, years, months, days).show()
    }
}