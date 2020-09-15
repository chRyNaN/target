package com.chrynan.target.example

import com.chrynan.target.core.KotlinTargetContainer

class Example {

    fun main(jsonString: String) {
        val container = KotlinTargetContainer.fromJsonString(jsonString)

        println("Targets")
        container.targets.forEach { println("targetName = ${it.targetName}") }
    }
}
