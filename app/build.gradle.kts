plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
    namespace = Android.applicationId
    compileSdk = Android.compileSdk

    defaultConfig {
        applicationId = Android.applicationId
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName

        testInstrumentationRunner = Android.testInstrumentalRunner

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isDebuggable = Obfuscation.releaseDebuggable
            isMinifyEnabled = Obfuscation.releaseMinifyEnabled
            isShrinkResources = Obfuscation.releaseMinifyEnabled

            signingConfig = signingConfigs.getByName("debug")

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
            )
        }
        debug {
            isDebuggable = Obfuscation.debugDebuggable
            isMinifyEnabled = Obfuscation.debugMinifyEnabled
            isShrinkResources = Obfuscation.debugMinifyEnabled

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                file("proguard-rules.pro")
            )
        }
    }
    compileOptions {
        sourceCompatibility = Config.compatibleJavaVersion
        targetCompatibility = Config.compatibleJavaVersion
    }
    kotlinOptions {
        jvmTarget = Config.jvmTarget
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    packagingOptions {
        resources {
            resources.excludes.add("META-INF/AL2.0")
            resources.excludes.add("META-INF/LGPL2.1")
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Libs.Compose.composeVersion
    }
}

dependencies {
    implementation ("com.google.android.gms:play-services-maps:18.1.0")
    implementation("pub.devrel:easypermissions:3.0.0")
//    implementation ("com.google.android:android-maps-utils:2.2.0")

    //Modules
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":common"))

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(Libs.Application.Navigation.navigation_fragment)
    implementation(Libs.Application.Navigation.navigation_ui)
    implementation(Libs.View.fragmentKtx)
    implementation(Libs.View.material)
    implementation(Libs.View.appCompat)

    //Kotlin
    implementation(Libs.View.coreKtx)

    //Firebase
    implementation(platform(Firebase.firebaseBom))
    implementation(Firebase.storage)
    implementation(Firebase.firestore)
    implementation(Firebase.auth)

    //Lifecycle
    implementation(Libs.View.lifecycleRuntime)
    implementation(Libs.View.lifecycleViewModel)

    //DI
    implementation(Libs.Application.DependencyInjection.hilt)
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")
    kapt(Libs.Application.DependencyInjection.hilt_compiler)

    //Compose
    implementation(Libs.Compose.coilCompose)
    implementation(Libs.Compose.material3)
    implementation(Libs.Compose.runtime)
    implementation(Libs.Compose.extended_icons)
    implementation(Libs.Boom.activityCompose)
    implementation(Libs.Boom.viewModelCompose)
    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.navigation)
    implementation(Libs.Compose.systemUiController)
    implementation(Libs.Compose.preview)
    implementation(Libs.Compose.tooling)
    implementation(Libs.Application.DependencyInjection.hiltNavigationCompose)

    //Tests
    testImplementation(Libs.View.Test.jUnit)
    androidTestImplementation(Libs.View.AndroidTest.jUnit)
    androidTestImplementation(Libs.View.AndroidTest.espresso)

}