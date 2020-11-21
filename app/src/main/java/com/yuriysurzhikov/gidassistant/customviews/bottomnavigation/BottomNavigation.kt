package com.yuriysurzhikov.gidassistant.customviews.bottomnavigation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.children
import com.yuriysurzhikov.gidassistant.R

class BottomNavigation : LinearLayout, SwipeableNavigation {

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)

    var navigationListener: NavigationListener? = null

    private val childList = mutableListOf<TextView>()

    init {
        val layout = LayoutInflater.from(context)
            .inflate(R.layout.navigation_bottom, this, true) as LinearLayout
        for (view in layout.children) {
            if (view is LinearLayout) {
                view.children.iterator().forEach { text ->
                    if (text is TextView) {
                        text.setOnClickListener {
                            invalidateViews(text)
                        }
                        childList.add(text)
                    }
                }
            }
        }
        if (childList.isNotEmpty())
            invalidateViews(childList[0])
    }

    private fun invalidateViews(clicked: TextView) {
        childList.forEach {
            it.setTextColor(resources.getColor(R.color.gidassistant_gray))
        }
        clicked.setTextColor(resources.getColor(R.color.gidassistant_accent))
        navigationListener?.onNavigationChanged(clicked)
    }

    override fun onPageSwiped(position: Int) {
        if (position < childList.size)
            invalidateViews(childList[position])
    }
}