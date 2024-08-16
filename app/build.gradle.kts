plugins {
    with(libs.plugins) {
        alias(jetbrains.kotlin.android)
        alias(android.application)
        alias(kotlinKapt)
    }
    id("kotlin-parcelize")
}

android {
    namespace = "com.picpay.desafio.android"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.picpay.desafio.android"
        minSdk = 21
        //noinspection ExpiredTargetSdkVersion
        targetSdk = 29
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    buildFeatures {
        compose = true
        viewBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
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

    implementation(libs.koin.androidx.compose)
    implementation(libs.koin.android)
    implementation(libs.koin.android.test)

    implementation(libs.dagger)
    implementation(libs.daggerCompiler)

    implementation(libs.rxjava)
    implementation(libs.rxandroid)

    implementation(libs.gson)

    implementation(libs.retrofit)
    implementation(libs.retrofitAdapterRxjava2)
    implementation(libs.retrofitConverterGson)
    implementation(libs.okhttp)
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
