@file:Suppress("SameParameterValue", "unused")

package com.chrynan.target.plugin

import com.chrynan.target.core.KotlinTargetContainer
import com.chrynan.target.plugin.util.ColorHandler
import com.chrynan.target.plugin.util.file
import com.chrynan.target.plugin.util.timestampForNow
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

class TargetPlugin : Plugin<Project> {

    private val mapper = KotlinTargetMapper()

    companion object {

        private const val GROUP_NAME = "kotlinTarget"
        private const val TASK_NAME = "generateKotlinTargetData"

        private const val DEFAULT_JSON_FILE_NAME = "targets.json"
        private const val DEFAULT_SVG_FILE_NAME_PREFIX = "target_"
        private const val DEFAULT_SVG_SUB_DIRECTORY = "svg"
        private const val DEFAULT_OUTPUT_SUB_DIRECTORY = "targets"
    }

    override fun apply(project: Project) {
        val extension = project.extensions.create(TargetExtension.EXTENSION_NAME, TargetExtension::class.java)

        project.tasks.register(TASK_NAME) { task ->
            task.group = GROUP_NAME

            val kmpExtension = project.extensions.getByName("kotlin") as KotlinMultiplatformExtension

            task.doFirst {
                val outputPath = extension.outputPath ?: "${project.projectDir.path}/$DEFAULT_OUTPUT_SUB_DIRECTORY"

                if (extension.generateJson) generateJson(outputPath, kmpExtension)

                if (extension.generateSvg) generateSvgs(outputPath, extension.colors, kmpExtension)
            }
        }
    }

    private fun generateJson(outputPath: String, kmpExtension: KotlinMultiplatformExtension) {
        val file = file(directoryPath = outputPath, fileName = DEFAULT_JSON_FILE_NAME)

        if (!file.exists()) file.createNewFile()

        val targets = kmpExtension.targets.map { mapper.map(it) }

        val container = KotlinTargetContainer(lastUpdated = timestampForNow(), targets = targets)

        file.writeText(container.toJsonString())
    }

    private fun generateSvgs(
        outputPath: String,
        colors: List<KotlinTargetColor> = emptyList(),
        kmpExtension: KotlinMultiplatformExtension
    ) {
        val targets = kmpExtension.targets.map { mapper.map(it) }
        val colorHandler = ColorHandler(colors)

        targets.forEach {
            val startPath = if (outputPath.endsWith("/")) outputPath else "$outputPath/"

            val file = file(
                directoryPath = startPath + DEFAULT_SVG_SUB_DIRECTORY,
                fileName = getSvgFileName(filePrefix = DEFAULT_SVG_FILE_NAME_PREFIX, targetName = it.name)
            )

            if (!file.exists()) file.createNewFile()

            val text = it.name
            val backgroundColor = colorHandler.getBackgroundColor(text)
            val textColor = colorHandler.getTextColor(text)

            val fileText = """
                |<svg xmlns="https://www.w3.org/2000/svg" width="200" height="100" viewBox="0 0 200 100">
                |    <rect x="0" y="0" width="200" height="100" stroke="$backgroundColor" stroke-width="5px" fill="$backgroundColor"/>
                |    <text x="50%" y="50%" dominant-baseline="middle" text-anchor="middle" fill="$textColor">$text</text>
                |</svg>
            """.trimMargin()

            file.writeText(fileText)
        }
    }

    private fun getSvgFileName(targetName: String, filePrefix: String = DEFAULT_SVG_FILE_NAME_PREFIX): String =
        "$filePrefix$targetName.svg"
}
