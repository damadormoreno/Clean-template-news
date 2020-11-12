// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    //val kotlin_version by extra("1.3.72")
    repositories {
        google()
        jcenter()
        
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.0.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.0")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        // Si necesitamos introducir con url
        //maven { url = uri("https://jitpack.io") }
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}
