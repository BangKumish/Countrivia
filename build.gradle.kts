// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.google.devtools.ksp") version "1.8.10-1.0.9" apply false
    id("androidx.navigation.safeargs.kotlin") version "2.5.1" apply false
}

buildscript {
    dependencies{
        classpath("androidx.navigation.safeargs:androidx.navigation.safeargs.gradle.plugin:2.7.5")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48.1")
    }
}
