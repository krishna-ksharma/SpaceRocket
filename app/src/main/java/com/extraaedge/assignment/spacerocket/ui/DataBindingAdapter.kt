package com.extraaedge.assignment.spacerocket.ui.home

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.extraaedge.assignment.spacerocket.R

@BindingAdapter("rocketName")
fun formatRocketName(textView: TextView, name: String) {
    textView.text = textView.context.getString(R.string.rocket_name, name)
}

@BindingAdapter("rocketCountry")
fun formatRocketCountry(textView: TextView, country: String) {
    textView.text = textView.context.getString(R.string.rocket_country, country)
}

@BindingAdapter("rocketEngineCount")
fun formatRocketEngineCount(textView: TextView, count: Number) {
    textView.text = textView.context.getString(R.string.rocket_engine_count, count)
}

@BindingAdapter("description")
fun formatDescription(textView: TextView, description: String) {
    textView.text =
        textView.context.getString(R.string.rocket_country_detail_description, description)
}

@BindingAdapter("cpl")
fun formatCPL(textView: TextView, number: Number) {
    textView.text =
        textView.context.getString(R.string.rocket_country_detail_cost_per_launch, number)
}

@BindingAdapter("active")
fun formatStatus(textView: TextView, status: Boolean) {
    textView.text = textView.context.getString(
        R.string.rocket_country_detail_active,
        if (status) "Yes" else "No"
    )
}

@BindingAdapter("height")
fun formatHeight(textView: TextView, height: String) {
    textView.text = textView.context.getString(R.string.rocket_country_detail_height, height)
}

@BindingAdapter("diameter")
fun formatDiameter(textView: TextView, height: String) {
    textView.text = textView.context.getString(R.string.rocket_country_detail_diameter, height)
}

@BindingAdapter("srp")
fun formatSRP(textView: TextView, number: Number) {
    textView.text =
        textView.context.getString(R.string.rocket_country_detail_success_rate_percentage, number)
}

@BindingAdapter("rocketImage")
fun loadImage(imageView: ImageView, url: String) {
    imageView.load(url) {
        crossfade(true)
    }
}