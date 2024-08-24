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
        buildConfigField("String", "BASE_URL", libs.versions.baseUrl.get())
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
    api(libs.androidx.core.ktx)
    api(libs.androidx.lifecycle.runtime.ktx)

    api(libs.androidx.activity.compose)
//    api(platform(libs.androidx.compose.bom))

    api(libs.androidx.ui)
    api(libs.androidx.ui.graphics)
    api(libs.androidx.ui.tooling.preview)
    api(libs.androidx.material3)
    api(libs.androidx.compose.material3.windowSizeClass)
    api(libs.navigation.compose)
    api(libs.coil.compose)

    androidTestApi(libs.androidx.junit)
    androidTestApi(libs.androidx.espresso.core)
    androidTestApi(libs.androidx.ui.test.junit4)
    debugApi(libs.androidx.ui.tooling)
    debugApi(libs.androidx.ui.test.manifest)

    api(libs.androidxAppcompat)
    api(libs.androidxConstraintLayout)
//    api(libs.material)

    api(libs.dagger)
    api(libs.daggerCompiler)

    api(libs.rxjava)
    api(libs.rxandroid)

    api(libs.koin.androidx.compose)
    api(libs.koin.android)

    api(libs.retrofit)
    api(libs.retrofitAdapterRxjava2)
    api(libs.retrofitConverterGson)
    api(libs.okhttp)
    api(libs.okhttp.interceptor)
    api(libs.okhttpMockwebserver)

    api(libs.picasso)
    api(libs.circleImageView)


    api(libs.koin.workmanager)
}