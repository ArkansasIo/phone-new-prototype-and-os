plugins {
    kotlin("multiplatform") version "2.2.20" apply false
    kotlin("android") version "2.2.20" apply false
    id("com.android.application") version "8.13.0" apply false
}

allprojects {
    group = "io.arkansas.universalphone"
    version = "0.1.0-prototype"
}
