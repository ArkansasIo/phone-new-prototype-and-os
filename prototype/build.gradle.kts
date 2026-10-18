plugins {
    kotlin("jvm") version "2.2.20"
    application
}

kotlin { jvmToolchain(21) }

application {
    mainClass.set("io.arkansas.universalphone.prototype.MainKt")
}

dependencies {
    implementation(project(":shared"))
    testImplementation(kotlin("test"))
}
