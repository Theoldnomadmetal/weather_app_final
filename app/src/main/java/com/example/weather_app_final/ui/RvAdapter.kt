package com.example.weather_app_final.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_app_final.R
import com.example.weather_app_final.data.forecast2.ForecastItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_layout.view.*

class RvAdapter: RecyclerView.Adapter<RvHolder>() {

    private val list = arrayListOf<ForecastItem>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return RvHolder(view)
    }
    fun update(list : List<ForecastItem>?){
        if (list!= null){
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RvHolder, position: Int) {
        holder.bind(list[position])
    }
}
class RvHolder(v: View) : RecyclerView.ViewHolder(v){
    fun bind(forecastItem: ForecastItem){
        itemView.tv_week_temp.text = forecastItem.main.temp.toString()
        itemView.temp_recycler_max.text = forecastItem.main.temp_max.toString()
        itemView.temp_recycler_min.text = forecastItem.main.temp_min.toString()
        val icon = forecastItem.weather.first().icon
        Picasso.with(itemView.context).load("http://openweathermap.org/img/w/$icon.png").into(itemView.icons_recycler)

    }
}