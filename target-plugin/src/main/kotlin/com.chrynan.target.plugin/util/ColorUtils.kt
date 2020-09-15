package com.chrynan.target.plugin.util

import com.chrynan.target.plugin.KotlinTargetColor
import kotlin.random.Random

class ColorHandler(private val colors: List<KotlinTargetColor>) {

    private val defaultColors = listOf(
        "#3AD4FF",
        "#6BAC25",
        "#FF9B00",
        "#4B4BFF"
    )

    private val backgroundColorMap = mutableMapOf<String, String>()
    private val textColorMap = mutableMapOf<String, String>()

    fun getBackgroundColor(name: String): String {
        val kotlinTargetColor = colors.firstOrNull { it.name == name }

        val color = kotlinTargetColor?.backgroundColor ?: backgroundColorMap[name] ?: getRandomColor()

        backgroundColorMap[name] = color

        return color
    }

    fun getTextColor(name: String): String {
        val kotlinTargetColor = colors.firstOrNull { it.name == name }

        val color = kotlinTargetColor?.textColor ?: textColorMap[name] ?: getRandomColor()

        textColorMap[name] = color

        return color
    }

    private fun getRandomColor(): String {
        val index = Random.nextInt(defaultColors.size)

        return defaultColors[index]
    }
}
