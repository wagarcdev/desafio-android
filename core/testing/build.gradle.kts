plugins {
    with(libs.plugins) {
        alias(jetbrains.kotlin.android)
        alias(android.library)
    }
}

android {
    namespace = libs.versions.appId.get() + ".core.testing"
    compileSdk = libs.versions.androidApiCompile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidApiMin.get().toInt()

        testInstrumentationRunner = "com.picpay.desafio.android.testing.InstrumentationTestRunner"
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

//    implementation(project(":feature:contacts"))


    api(libs.junit)
    api(libs.mockitoCore)
    api(libs.mockitoKotlin)
    api(libs.coreTesting)

    api(libs.androidx.test.core)
    api(libs.androidx.test.coreKtx)

    api(libs.kotlinx.coroutines.test)

    api(libs.koin.android.test)
    api(libs.koin.android.testJU4)
    api(libs.koin.androidx.compose)
    api(libs.koin.core)
    api(libs.koin.workmanager)

    api(libs.testRunner)
    androidTestApi(libs.androidx.espresso.core)
    androidTestImplementation(project(":core:network"))
    androidTestImplementation(project(":core:common"))
    androidTestImplementation(project(":core:domain"))

}