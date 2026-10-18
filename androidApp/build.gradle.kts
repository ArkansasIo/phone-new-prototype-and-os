plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "io.arkansas.universalphone.android"
    compileSdk = 36

    defaultConfig {
        applicationId = "io.arkansas.universalphone"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "0.1.0-prototype"
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.activity:activity-compose:1.11.0")
    implementation("androidx.compose.ui:ui:1.9.0")
    implementation("androidx.compose.material3:material3:1.3.2")
}
