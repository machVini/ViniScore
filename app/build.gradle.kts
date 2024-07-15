import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.dagger.hilt.plugin)
    alias(libs.plugins.compose.compiler)
    kotlin("plugin.serialization") version "2.0.0"
}

android {
    namespace = "com.vini.football.center.news"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.vini.football.center.news"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        buildConfigField("String", "FOOTBALL_BASE_URL", gradleLocalProperties(rootDir, providers).getProperty("FOOTBALL_BASE_URL"))
        buildConfigField("String", "FOOTBALL_API_KEY", gradleLocalProperties(rootDir, providers).getProperty("FOOTBALL_API_KEY"))
        buildConfigField("String", "NEWS_BASE_URL", gradleLocalProperties(rootDir, providers).getProperty("NEWS_BASE_URL"))
        buildConfigField("String", "NEWS_API_KEY", gradleLocalProperties(rootDir, providers).getProperty("NEWS_API_KEY"))

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

dependencies {
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Coil
    implementation(libs.coil.compose)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    // Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphics)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.compose.material.icons.extended)
    implementation(libs.compose.activity.compose)
    implementation(libs.compose.runtime.livedata)
    implementation(libs.compose.lifecycle.viewmodel.compose)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    implementation(libs.navigation.compose)
    implementation(libs.lifecycle.runtime.compose.android)
    implementation(libs.activity.compose)
    debugImplementation(libs.compose.ui.tooling)


    // Dagger-Hilt
    implementation(libs.dagger.hilt)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.dagger.hilt.compiler)

    // AndroidX and other libraries
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.material.icons.extended)
    implementation(libs.kotlinx.serialization.json)

    // Test dependencies
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.dagger.hilt.testing)
}