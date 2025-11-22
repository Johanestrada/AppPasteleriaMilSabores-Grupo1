plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.adarshr.test-logger") version "3.2.0"

// Agregar esto para habilitar KAPT

    kotlin("kapt")

}

android {
    namespace = "com.example.proyectologin006d_final"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.proyectologin006d_final"
        minSdk = 24
        targetSdk = 36
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
            sourceCompatibility = JavaVersion.VERSION_21
            targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.2")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)


    // Dependencia para la navegación con Jetpack Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // CameraX
    implementation("androidx.camera:camera-core:1.3.4")
    implementation("androidx.camera:camera-camera2:1.3.4")
    implementation("androidx.camera:camera-lifecycle:1.3.4")
    implementation("androidx.camera:camera-view:1.3.4")
    implementation("com.google.accompanist:accompanist-permissions:0.32.0")
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation("androidx.compose.runtime:runtime-livedata:1.6.8")

// Íconos (core opcional) y EXTENDIDOS (¡este es el clave!)
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material:material-icons-extended")


    // Dependencias Room
    implementation("androidx.room:room-runtime:2.6.1")  // Versión actualizada
    kapt("androidx.room:room-compiler:2.6.1")          // Misma versión
    implementation("androidx.room:room-ktx:2.6.1")     // Misma versión

    // Retrofit y Gson para API REST
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)

    // Testing - Kotest
    testImplementation(libs.kotest.runner)
    testImplementation(libs.kotest.assertions)

    // Testing - MockK
    testImplementation(libs.mockk)

    // Testing - Coroutines
    testImplementation(libs.coroutines.test)
    testImplementation(libs.junit5.api)
    testRuntimeOnly(libs.junit5.engine)

    // Testing - AndroidX
    testImplementation("androidx.arch.core:core-testing:2.2.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

tasks.withType<Test> {
    useJUnitPlatform() // <<< NECESARIO

    // Configuración mínima de logging y plugin Test Logger para salida tipo tabla
    testLogging {
        events("passed", "failed", "skipped")
    }

    // Configuración del plugin 'com.adarshr.test-logger' para una salida más visual en consola
    // Usa el tema MOCHA para formato tipo tabla/summary
    try {
        @Suppress("UNCHECKED_CAST")
        val testLogger = project.extensions.findByName("testlogger")
        if (testLogger != null) {
            // configuraciones que el plugin entiende; si el plugin no está aplicado, esto no rompe
        }
    } catch (_: Exception) {
        // ignore
    }
}