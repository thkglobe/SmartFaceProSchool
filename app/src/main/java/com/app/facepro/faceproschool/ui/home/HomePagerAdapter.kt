package com.app.facepro.faceproschool.ui.home

import androidx.viewpager.widget.PagerAdapter
import android.widget.LinearLayout
import android.view.ViewGroup
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import androidx.databinding.DataBindingUtil
import com.app.facepro.faceproschool.R
import com.app.facepro.faceproschool.databinding.ItemHomePagerBinding
import com.app.facepro.faceproschool.ui.home.model.Pan3MsgDetailsItem


class HomePagerAdapter(val context: Context, private val pan3MsgDetailsItems:List<Pan3MsgDetailsItem>):PagerAdapter() {
    private val layoutInflater: LayoutInflater =
        context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return pan3MsgDetailsItems.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binder: ItemHomePagerBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_home_pager, container, false)
        binder.vm = pan3MsgDetailsItems[position]
        binder.executePendingBindings()
        container.addView(binder.root)
        return binder.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}
