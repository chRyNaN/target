# target
Kotlin Multi-platform Target Gradle Plugin. This Gradle Plugin generates a JSON file, and optional SVGs, for the available Kotlin Targets in a Kotlin Multi-platform Module.
This allows easy access to which Kotlin Targets a module supports, for example in a README file.

## Using the library

### Using the Plugin

* Apply the plugin
```groovy
apply plugin: "com.chrynan.target.plugin"
```

* Setup the extension
```groovy
kotlinTargetDataGenerator {
    generateJson = true
    generateSvg = true
    outputPath = "path/to/generated/output"
}
```

* Run the Gradle Task
`./gradlew generateKotlinTargetData`

### Parsing the JSON Kotlin Targets

* Add the `target-core` module as a dependency
```groovy
implementation "com.chrynan.target:target-core:$VERSION"
```

* Provide the JSON String to the `KotlinTargetContainer.fromJsonString()` function
```kotlin
val container = KotlinTargetContainer.fromJsonString(jsonStringValue)
```

## License
```
Copyright 2020 chRyNaN

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
