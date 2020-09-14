package com.chrynan.target.plugin.util

import com.chrynan.target.plugin.KotlinTargetColor

class ColorHandler(private val colors: List<KotlinTargetColor>) {

    fun getBackgroundColor(name: String): String {
        val color = colors.firstOrNull { it.name == name }

        return color?.backgroundColor ?: ""
    }

    fun getTextColor(name: String): String {
        val color = colors.firstOrNull { it.name == name }

        return color?.textColor ?: ""
    }
}
