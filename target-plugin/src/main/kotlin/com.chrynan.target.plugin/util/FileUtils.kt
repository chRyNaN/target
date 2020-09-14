package com.chrynan.target.plugin.util

import java.io.File

fun file(directoryPath: String, fileName: String): File {
    val path = if (directoryPath.endsWith("/")) directoryPath + fileName else "$directoryPath/$fileName"

    return File(path)
}
