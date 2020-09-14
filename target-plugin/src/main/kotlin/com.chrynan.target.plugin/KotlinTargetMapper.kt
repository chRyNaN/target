package com.chrynan.target.plugin

import com.chrynan.target.core.KotlinTarget

class KotlinTargetMapper {

    fun map(input: org.jetbrains.kotlin.gradle.plugin.KotlinTarget): KotlinTarget =
        KotlinTarget(
            targetName = input.targetName,
            name = input.name,
            platformType = input.platformType.name,
            components = input.components.map { it.name },
            publishable = input.publishable,
            artifactsTaskName = input.artifactsTaskName,
            defaultConfigurationName = input.defaultConfigurationName,
            apiElementsConfigurationName = input.apiElementsConfigurationName,
            runtimeElementsConfigurationName = input.runtimeElementsConfigurationName
        )
}
