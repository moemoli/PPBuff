import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("com.google.devtools.ksp")
}

android {
    namespace = "moe.imoli.ppbuff"
    compileSdk = 36

    defaultConfig {
        applicationId = "moe.imoli.ppbuff"
        minSdk = 29
        targetSdk = 35
        versionCode = 20
        versionName = "2.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin{
        compilerOptions{
            jvmTarget = JvmTarget.JVM_17
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    val yuki = "1.3.0"
    val kavaref = "1.0.1"
    val nav = "1.0.0-alpha07"


    // 基础依赖
    implementation("com.highcapable.yukihookapi:api:$yuki")
    // 推荐使用 KavaRef 作为核心反射 API
    implementation("com.highcapable.kavaref:kavaref-core:$kavaref")
    implementation("com.highcapable.kavaref:kavaref-extension:$kavaref")
    // 作为 Xposed 模块使用务必添加，其它情况可选
    compileOnly("de.robv.android.xposed:api:82")
    // 作为 Xposed 模块使用务必添加，其它情况可选
    ksp("com.highcapable.yukihookapi:ksp-xposed:$yuki")
    implementation("androidx.core:core-ktx:1.17.0")
    // nav3
    implementation("androidx.navigation3:navigation3-ui:$nav")
    implementation("androidx.navigation3:navigation3-runtime:$nav")
    implementation("androidx.lifecycle:lifecycle-viewmodel-navigation3:$nav")
    implementation("androidx.compose.material3.adaptive:adaptive-navigation3:1.0.0-SNAPSHOT")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.8.1")

    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2")
    implementation("androidx.activity:activity-compose:1.10.1")
    implementation(platform("androidx.compose:compose-bom:2024.09.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.09.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}