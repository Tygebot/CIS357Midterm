import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application") version "8.6.0"
    id("org.jetbrains.kotlin.android") version "2.0.21"
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.21"
}



android {
    namespace = "com.example.gvsufoodmap"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.gvsufoodmap"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    // ✅ Align Java to 17
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures { compose = true }

    packaging { resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" } }
}

@Suppress("UnstableApiUsage")
kotlin {
    // Use the JDK you selected in Settings (JBR 21)
    jvmToolchain(21)
}

dependencies {
    val composeBom = platform("androidx.compose:compose-bom:2024.09.03")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation("androidx.activity:activity-compose:1.9.2")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.runtime:runtime")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    implementation("androidx.compose.material3:material3:1.3.0")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.navigation:navigation-compose:2.8.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.6")

    // 🔁 REMOVE this line:
    // implementation("com.google.maps.android:maps-compose:6.10.0")

    // ✅ ADD this: classic Google Maps SDK
    implementation("com.google.android.gms:play-services-maps:18.2.0")

    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.core:core:1.13.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.21")

    implementation("com.google.android.material:material:1.12.0")
}


configurations.all {
    resolutionStrategy {
        // Force older core versions that work with AGP 8.6.0 / compileSdk 35
        force("androidx.core:core-ktx:1.13.1")
        force("androidx.core:core:1.13.1")
        // make sure everything uses the same Kotlin stdlib as the plugin
        force("org.jetbrains.kotlin:kotlin-stdlib:2.0.21")
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}

