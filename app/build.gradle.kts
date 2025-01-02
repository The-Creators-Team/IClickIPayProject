plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms.google.services)
    //compose nav plugin
    alias(libs.plugins.kotlin.serialization)

}

android {
    namespace = "com.example.iclickipay"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.iclickipay"
        minSdk = 24
        targetSdk = 34
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
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.foundation.layout.android)
    implementation(project(":feature_chat"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Please separate any added dependencies with a blank line and a comment
    //with your name describing what the import is needed for
    //navigation with compose - Jonathan
    implementation(libs.navigation.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(project(":feature_mover"))



    //Map Dependencies - Sebastian

    implementation (libs.play.services.maps)
    implementation (libs.maps.compose)
    implementation (libs.places)
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation(project(":feature_babysitter"))
//    implementation(project(":feature_bank"))
    implementation(project(":feature_chat"))
    implementation(project(":feature_delivery"))
    implementation(project(":feature_eat"))
    implementation(project(":feature_handyman"))
    implementation(project(":feature_hotel"))
    implementation(project(":feature_housecleaning"))
    implementation(project(":feature_laundry"))
    implementation(project(":feature_learn"))
    implementation(project(":feature_mechanic"))
    implementation(project(":feature_mover"))
    implementation(project(":feature_pcrepair"))
    implementation(project(":feature_pet"))
}