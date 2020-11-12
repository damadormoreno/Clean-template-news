plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "com.deneb.newsapp"
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled =  false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    /*viewBinding {
        enabled = true
    }*/

    packagingOptions {
        exclude("LICENSE.txt")
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/ASL2.0")
        exclude("META-INF/NOTICE")
        exclude("META-INF/NOTICE")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    //Kotlin
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.10")
    //Kotlin Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.3.8")

    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.cardview:cardview:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("com.google.android.material:material:1.2.1")
    implementation("androidx.annotation:annotation:1.1.0")
    implementation("androidx.preference:preference-ktx:1.1.1")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.2.0")
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

    // Firebase Cloud Firestore (Kotlin)
    implementation("com.google.firebase:firebase-firestore-ktx:22.0.0")

    // KOIN
    implementation("org.koin:koin-android:2.1.6")
    implementation("org.koin:koin-android-viewmodel:2.1.6")
    implementation("org.koin:koin-android-scope:2.1.6")

    //Navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.1")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.1")


    kapt("androidx.room:room-compiler:2.2.5")
    compileOnly("javax.annotation:jsr250-api:1.0")
    compileOnly("javax.inject:javax.inject:1")

    implementation("org.jetbrains.anko:anko:0.10.8")

    implementation("com.github.bumptech.glide:glide:4.11.0")

    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.0")
    implementation("androidx.room:room-runtime:2.2.5")

    implementation("com.michael-bull.kotlin-result:kotlin-result:1.1.6")

    // Unit Testing
    testImplementation("junit:junit:4.13.1")
    testImplementation("com.nhaarman:mockito-kotlin:1.5.0")
    testImplementation("org.mockito:mockito-core:3.5.6")
    testImplementation("io.kotlintest:kotlintest:2.0.7")
    testImplementation("android.arch.core:core-testing:1.1.1")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    testImplementation("org.robolectric:robolectric:4.4")
    testImplementation("org.amshove.kluent:kluent-android:1.61")
    androidTestImplementation("com.android.support.test:runner:1.0.2")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:3.0.2")
    testImplementation("org.mockito:mockito-inline:3.4.6")


}
