package com.example.daysmaneger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterItems(private val list: List<ModelTask>) : RecyclerView.Adapter<AdapterItems.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name_item_view:TextView? = null
        var time_end_view:TextView? = null
        var big_time_view:TextView? = null
        var main_content:RelativeLayout? = null
        init {
            name_item_view = itemView.findViewById(R.id.name_item)
            time_end_view = itemView.findViewById(R.id.time_item)
            big_time_view = itemView.findViewById(R.id.big_time_items)
            main_content = itemView.findViewById(R.id.main_content_item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list[position].system_time){
            holder.big_time_view?.text = list[position].time_end.substringBefore(':') + ":00"
            holder.main_content?.visibility = View.GONE
            holder.big_time_view?.visibility = View.VISIBLE
        }else {
            holder.name_item_view?.text = list[position].name
            holder.time_end_view?.text = "Выполнить до " + list[position].time_end
        }
    }
}