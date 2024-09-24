import com.android.ide.common.repository.main
import org.gradle.kotlin.dsl.sourceSets

plugins {
    id("com.android.application")
}

android {
    aaptOptions.cruncherEnabled = false
    aaptOptions.useNewCruncher = false

    namespace = "net.micode.wcnm"
    compileSdk = 34

    defaultConfig {
        applicationId = "net.micode.wcnm"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}



dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.6.0")
    implementation("androidx.navigation:navigation-ui:2.6.0")
    implementation(files("libs\\okhttputils-2_6_2.jar"))
    implementation(files("libs\\banner-1.5.0.aar"))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.facebook.fresco:fresco:2.5.0") // 使用最新版本号

    implementation("de.hdodenhof:circleimageview:2.1.0")

    implementation("com.squareup.okhttp3:mockwebserver:3.11.0")
    implementation("com.squareup.okio:okio:2.0.0")


    implementation("com.github.lovetuzitong:MultiImageSelector:1.2")

    // 插件升级后出现kotlin-stdlib-jdk版本冲突在这里解决
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.20")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.20")

    implementation(files("libs\\AMap3DMap_10.0.900_AMapSearch_9.7.3_AMapLocation_6.4.7_20240816.jar"))
//    implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation("pub.devrel:easypermissions:3.0.0")

}

