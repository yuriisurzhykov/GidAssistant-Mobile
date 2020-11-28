package com.yuriysurzhikov.gidassistant.customviews.bottomnavigation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import com.yuriysurzhikov.gidassistant.R

class BottomNavigation : LinearLayout, SwipeableNavigation {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    var navigationListener: NavigationListener? = null

    private val childList = mutableListOf<TextView>()

    init {
        val layout = LayoutInflater.from(context)
            .inflate(R.layout.navigation_bottom, this, true) as LinearLayout
        for (view in layout.children) {
            if (view is LinearLayout) {
                for((position, text) in view.children.withIndex()) {
                    if (text is TextView) {
                        childList.add(text)
                        text.setOnClickListener {
                            invalidateViews(position, true)
                        }
                    }
                }
            }
        }
        if (childList.isNotEmpty())
            invalidateViews(0, true)
    }

    private fun invalidateViews(position: Int, needForNavigation: Boolean) {
        childList.forEach {
            it.setTextColor(resources.getColor(R.color.gidassistant_gray))
        }
        childList[position].setTextColor(resources.getColor(R.color.gidassistant_accent))
        if(needForNavigation)
            navigationListener?.onNavigationChanged(position)
    }

    override fun onPageSwiped(position: Int, needForNavigation: Boolean) {
        if (position < childList.size)
            invalidateViews(position, needForNavigation)
    }
}