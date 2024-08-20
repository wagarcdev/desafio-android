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
            sourceCompatibility(libs.versions.java.get())
            targetCompatibility(libs.versions.java.get())
        }

        buildFeatures {
            compose = true
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
    implementation(project(":core:database"))
    implementation(project(":core:network"))
    implementation(project(":core:common"))
    implementation(project(":core:domain"))
    implementation(project(":core:design"))

    implementation(project(":feature:contacts"))

    implementation(project(":sync:work"))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
}
