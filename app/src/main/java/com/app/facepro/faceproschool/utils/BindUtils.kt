package com.app.facepro.faceproschool.utils

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.app.facepro.faceproschool.common.AutoScrollViewPager
import com.app.facepro.faceproschool.common.Result
import com.app.facepro.faceproschool.ui.home.HomePagerAdapter
import com.app.facepro.faceproschool.ui.home.model.Pan3MsgDetailsItem
import com.squareup.picasso.Picasso
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator

@BindingAdapter("progressState")
fun ProgressBar.progressState(result: Result?) {
    result?.let {
        when (it) {
            is Result.Progress -> {
                this.visibility = View.VISIBLE
            }
            else -> {
                this.visibility = View.GONE
            }
        }
    } ?: run {
        this.visibility = View.GONE
    }
}

@BindingAdapter("dateTimeConverter")
fun TextView.dateTime(date: String) {
    date?.let {
        this.text = getDisplayableTime(date)
    }
}

@BindingAdapter("android:visibility")
fun View.bindVisibility(visibleOrGone: Boolean) {
    val visibility = if (visibleOrGone) View.VISIBLE else View.GONE
    this.visibility = visibility
}

@BindingAdapter(value = ["pagerAdapter", "indicator"])

fun AutoScrollViewPager.bindAdapter(
    items: List<Pan3MsgDetailsItem>?,
    indicator: WormDotsIndicator
) {
    items?.let {
        interval = 4000
        setScrollDurationFactor(3.0)
        startAutoScroll()
        this.adapter = HomePagerAdapter(context, items)
        indicator.setViewPager(this)
    }
}

@BindingAdapter("srcImage")
fun ImageView.bindPicassoImage(url: String?) {
    Picasso.get().load(url).fit().into(this)
}
