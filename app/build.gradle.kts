
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
//    id ("com.google.dagger.hilt.android")
//    kotlin("kapt")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    viewBinding {
        enable = true
    }

}

dependencies {

    implementation("androidx.core:core-splashscreen:1.0.0")

    implementation(libs.androidx.core.ktx)
    //implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)


    implementation(libs.androidx.ui.test.android)
    implementation(libs.androidx.appcompat)

    testImplementation(libs.junit)
    testImplementation(libs.junit.jupiter)
    testImplementation ("org.mockito:mockito-core:4.0.0")
    testImplementation ("org.mockito.kotlin:mockito-kotlin:4.0.0")


    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.0")


   /* implementation(libs.ktor.server.netty)*/
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.cio)


    implementation (libs.retrofit )// Retrofit
    implementation (libs.converter.gson) // Gson converter for Retrofit


    implementation(libs.androidx.lifecycle.viewmodel.ktx )// ViewModel
    implementation(libs.androidx.lifecycle.runtime.ktx) // LiveData

    implementation("androidx.fragment:fragment-ktx:1.6.2")

    implementation ("com.jakewharton.threetenabp:threetenabp:1.4.6")

    implementation(project(":data"))
    implementation(project(":domain"))
    androidTestImplementation(libs.junit.jupiter)

}