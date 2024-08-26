plugins {
    with(libs.plugins) {
        alias(jetbrains.kotlin.android)
        alias(android.library)
    }
}

android {
    namespace = libs.versions.appId.get() + ".core.commom"
    compileSdk = libs.versions.androidApiCompile.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidApiMin.get().toInt()
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            merges += "META-INF/LICENSE.md"
            merges += "META-INF/LICENSE-notice.md"
        }
    }


}

dependencies {
    implementation(project(":core:testing"))

    api(libs.androidx.core.ktx)
    api(libs.androidx.lifecycle.runtime.ktx)

    api(libs.androidx.activity.compose)

    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.tooling.preview)
    debugApi(libs.androidx.ui.tooling)
    api(libs.androidx.material3)
    api(libs.androidx.compose.material3.windowSizeClass)
    api(libs.navigation.compose)
    api(libs.coil.compose)

    api(libs.kotlinx.coroutines.android)

    api(libs.dagger)
    api(libs.daggerCompiler)

    api(libs.koin.androidx.compose)
    api(libs.koin.android)

    api(libs.retrofit)
    api(libs.retrofitConverterGson)
    api(libs.okhttp)
    api(libs.okhttp.interceptor)
    api(libs.okhttpMockwebserver)

    api(libs.koin.workmanager)
}