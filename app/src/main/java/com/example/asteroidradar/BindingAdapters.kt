package com.example.asteroidradar

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import androidx.compose.ui.res.stringResource
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.asteroidradar.main.CustomAdapter
import com.example.asteroidradar.model.Asteroid
import com.squareup.picasso.Picasso


@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.apply {
         setImageResource(R.drawable.ic_status_potentially_hazardous)
            contentDescription = resources.getString(R.string.potentially_hazardous_asteroid_image)
        }

    } else {
        imageView.apply {
            setImageResource(R.drawable.ic_status_normal)
            contentDescription = resources.getString(R.string.not_hazardous_asteroid_image)
        }
        }
    }


@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.apply {
            setImageResource(R.drawable.asteroid_hazardous)
            contentDescription = resources.getString(R.string.potentially_hazardous_asteroid_image)
        }
    } else {
        imageView.apply { setImageResource(R.drawable.asteroid_safe)
            contentDescription = resources.getString(R.string.not_hazardous_asteroid_image)}
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}


@BindingAdapter("imgUrl")
fun bindPictureOfTheDay(imageView: ImageView , pictureOfDay: PictureOfDay?){
    Picasso
        .with(imageView.context)
        .load(pictureOfDay?.url)
        .error(R.drawable.network_error)
        //.placeholder(R.drawable.placeholder_picture_of_day)
        .into(imageView)
        imageView.contentDescription = pictureOfDay?.title
}

@BindingAdapter("fullImg")
fun bindFullPictureOfTheDay(imageView: ImageView , pictureOfDay: PictureOfDay?){
    Picasso
        .with(imageView.context)
        .load(pictureOfDay?.url)
        .error(R.drawable.network_error)
        //.placeholder(R.drawable.placeholder_picture_of_day)
        .into(imageView)
    imageView.contentDescription = pictureOfDay?.explanation
}
