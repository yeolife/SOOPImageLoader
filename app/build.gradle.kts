plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.soopimageloader"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.soopimageloader"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Lifecycle
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7")
    implementation ("androidx.fragment:fragment-ktx:1.8.5")

    // 태그 UI
    implementation ("com.google.android.flexbox:flexbox:3.0.0")

    // 당겨서 새로고침
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Paging3
    implementation ("androidx.room:room-paging:2.6.1")
    implementation ("androidx.paging:paging-runtime-ktx:3.3.5")

    // Image Loader
    implementation("com.github.bumptech.glide:glide:4.12.0")

    // Networking
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")

    // DI
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")

    // Room DB
    implementation("androidx.room:room-runtime:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")
}