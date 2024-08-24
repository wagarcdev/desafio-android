plugins {
    with(libs.plugins) {
        alias(jetbrains.kotlin.android)
        alias(android.library)
        alias(kotlinKapt)
    }
}

android {
    namespace = libs.versions.appId.get() + ".core.database"
    compileSdk = libs.versions.androidApiCompile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidApiMin.get().toInt()

        testInstrumentationRunner = "com.picpay.desafio.android.core.testing.InstrumentationTestRunner"

    }

    compileOptions {
        sourceCompatibility(libs.versions.java.get())
        targetCompatibility(libs.versions.java.get())
    }

    kotlinOptions {
        val warningsAsErrors: String? by project
        allWarningsAsErrors = warningsAsErrors.toBoolean()

        jvmTarget = libs.versions.java.get()

        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn"
        )
    }


}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:testing"))

    api(libs.roomKtx)
    api(libs.roomRuntime)
    implementation(project(":core:data"))
    kapt(libs.roomCompiler)
}