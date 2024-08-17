plugins {
    with(libs.plugins) {
        alias(jetbrains.kotlin.android)
        alias(android.application)
        alias(kotlinKapt)
    }
    id("kotlin-parcelize")
}

android {
    with(libs.versions) {
        namespace = appId.get()
        compileSdk = androidApiCompile.get().toInt()

        defaultConfig {
            applicationId = appId.get()
            minSdk =  androidApiMin.get().toInt()
            //noinspection ExpiredTargetSdkVersion
            targetSdk =  androidApiTarget.get().toInt()
            versionCode = appVersionCode.get().toInt()
            versionName = appVersionName.get()

            testInstrumentationRunner = instrumentationTestRunner.get()
            vectorDrawables {
                useSupportLibrary = true
            }
        }
        buildTypes {
            release {
                isMinifyEnabled = false
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        buildFeatures {
            compose = true
            viewBinding = true
        }
        composeOptions {
            kotlinCompilerExtensionVersion = kotinExtensionVersion.get()
        }
        packaging {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
            }
        }
        kotlinOptions {
            jvmTarget = java.get()
        }
    }
}

dependencies {

    implementation(project(":core:data"))
    implementation(project(":core:domain"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.androidxAppcompat)
    implementation(libs.androidxConstraintLayout)
    implementation(libs.material)

    implementation(libs.dagger)
    implementation(libs.daggerCompiler)

    implementation(libs.rxjava)
    implementation(libs.rxandroid)

    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.android)
    implementation(libs.koin.android.test)

    implementation(libs.retrofit)
    implementation(libs.retrofitAdapterRxjava2)
    implementation(libs.retrofitConverterGson)
    implementation(libs.okhttp)
    implementation(libs.okhttp.interceptor)
    implementation(libs.okhttpMockwebserver)

    implementation(libs.picasso)
    implementation(libs.circleImageView)

    testImplementation(libs.junit)
    testImplementation(libs.mockitoCore)
    testImplementation(libs.mockitoKotlin)
    testImplementation(libs.coreTesting)

    androidTestImplementation(libs.testRunner)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.coreKtxTest)
}
