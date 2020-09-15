package com.example.weather_app_final.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.weather_app_final.R
import com.example.weather_app_final.data.RetrofitBuilder
import com.example.weather_app_final.data.current.CurrentModel
import com.example.weather_app_final.data.forecast2.ForecastModel2
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private val adapter = RvAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        currentDate()
        current()
        forecast2()

        recycler.adapter = adapter


    }

    private fun forecast2() {
        RetrofitBuilder.getService()
            ?.getSecondForecast("Bishkek", getString(R.string.api_key), "metric")
            ?.enqueue(object : Callback<ForecastModel2> {
                override fun onFailure(call: Call<ForecastModel2>, t: Throwable) {
                    Log.d("callback", "Fail")
                }

                override fun onResponse(
                    call: Call<ForecastModel2>,
                    response: Response<ForecastModel2>
                ) {
                    setForecast2Views(response)
                }

            })
    }

    private fun currentDate(date: Int?): String {
        val hours = date?.toLong() ?: 0
        return SimpleDateFormat("H:mm", Locale.getDefault()).format(Date(hours * 1000))

    }

    private fun currentDate() {
        val dayFormat = SimpleDateFormat("d", Locale.getDefault())
        val date = Date()
        val day = dayFormat.format(date)
        tvDateNumber.text = day

        val monthsFormat = SimpleDateFormat("MMMM\nyyyy", Locale.getDefault())
        val months = monthsFormat.format(date)
        tvDateMonth.text = months
    }

    private fun current() {
        RetrofitBuilder.getService()?.getCurrent("Bishkek", getString(R.string.api_key), "metric")
            ?.enqueue(object : Callback<CurrentModel> {
                override fun onFailure(call: Call<CurrentModel>, t: Throwable) {
                    Log.d("retrofit", "fail")
                }

                override fun onResponse(
                    call: Call<CurrentModel>,
                    response: Response<CurrentModel>
                ) {
                    setCurrentViews(response)
                }

            })
    }

    private fun setCurrentViews(response: Response<CurrentModel>) {
        val data = response.body()
        temp_num.text = getString(R.string.degree, data?.main?.temp?.toShort().toString())
        temp_max.text = getString(R.string.degree, data?.main?.temp_max?.toShort().toString())
        min_number.text = getString(R.string.degree, data?.main?.temp_min?.toShort().toString())

        val windSpeed = data?.wind?.speed.toString() + getString(R.string.speed)
        wind_num.text = windSpeed

        val pressure = data?.main?.pressure.toString() + getString(R.string.pressureFormat)
        pressure_num.text = pressure

        val humidity = data?.main?.humidity.toString() + "%"
        humidity_num.text = humidity

        val cloudiness = data?.clouds?.all.toString() + "%"
        cloudiness_num.text = cloudiness

        val icon = data?.weather?.first()?.icon
        Picasso.with(this).load("http://api.openweathermap.org/img/w/$icon.png").into(image_weather)

        location.text = data?.name

        val sunrise = currentDate(response.body()?.sys?.sunset)
        sunrise_num.text = sunrise

        val sunset = currentDate(response.body()?.sys?.sunrise)
        sunset_num.text = sunset

        temp_desc.text = data?.weather?.first()?.description


    }

    private fun setForecast2Views(response: Response<ForecastModel2>) {
        if (response.isSuccessful && response.body() != null) {
            adapter.update(response.body()?.list)
        }
    }
}
