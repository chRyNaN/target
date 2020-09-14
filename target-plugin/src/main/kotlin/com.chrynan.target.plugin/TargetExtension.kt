package com.chrynan.target.plugin

open class TargetExtension {

    companion object {

        const val EXTENSION_NAME = "kotlinTargetDataGenerator"
    }

    var generateJson: Boolean = true
    var generateSvg: Boolean = true
    var colors: List<KotlinTargetColor> = emptyList()
    var outputPath: String? = null
}
