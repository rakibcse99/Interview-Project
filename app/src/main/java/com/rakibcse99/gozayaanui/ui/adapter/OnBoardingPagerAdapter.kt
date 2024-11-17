package com.rakibcse99.gozayaanui.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.rakibcse99.gozayaanui.R

class OnBoardingPagerAdapter(
    private val mContext: Context?,
    private val mImages: List<String>,

    ) : PagerAdapter() {


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater =
            mContext!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val layoutScreen: View = inflater.inflate(R.layout.layout_on_boarding_item, null)
        val imgSlide = layoutScreen.findViewById<ImageView>(R.id.slider_image)
        Glide.with(mContext)
            .load(mImages[position])
            .centerCrop()
            .into(imgSlide)


        container.addView(layoutScreen)
        return layoutScreen
    }

    override fun getCount(): Int {
        return mImages.size
    }

    override fun isViewFromObject(view: View, o: Any): Boolean {
        return view === o
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
