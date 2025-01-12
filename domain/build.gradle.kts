plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.domain"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

    androidTestImplementation ("androidx.test.espresso:espresso-core:3.5.0")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0" )// Retrofit
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0") // Gson converter for Retrofit
    implementation ("com.google.dagger:hilt-android:2.48" )// Hilt
    kapt            ("com.google.dagger:hilt-android-compiler:2.48") // Hilt compiler
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1" )// ViewModel
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1") // LiveData

}