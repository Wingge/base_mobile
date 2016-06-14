# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/wing/Library/Android/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Needed just to be safe in terms of keeping Google API service model classes
#-keep class com.google.api.services.*.model.* TODO
#-keep class com.google.api.client.** TODO
-keepattributes Signature,RuntimeVisibleAnnotations,AnnotationDefault


# Other settings
-keep class com.android.**
-keep class com.google.android.**
-keep class com.google.android.gms.**
-keep class com.google.android.gms.location.**
-keep class com.google.api.client.**
-keep class com.google.maps.android.**
-keep class libcore.**

-dontwarn com.nineoldandroids.view.animation.** #Required by SlidingUpPanel
-dontwarn org.junit.**
-dontwarn org.mockito.**
-dontwarn org.robolectric.**
-dontwarn com.bumptech.glide.GlideTest
-dontwarn com.google.common.cache.**
-dontwarn com.google.common.primitives.**
-dontwarn com.google.api.client.googleapis.**
-dontwarn com.sothree.slidinguppanel.**
-dontwarn sun.misc.Unsafe
-dontwarn com.google.common.util.concurrent.RateLimiter
##okhttp
#-keepattributes *Annotation*
#-keep class okhttp3.** { *; }
#-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**
##okio
-dontwarn okio.**
# Retrofit 2.X
-dontwarn retrofit2.**
#-keep class retrofit2.** { *; }
#-keepattributes Exceptions
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}
#realm
-keep class io.realm.annotations.RealmModule
-keep @io.realm.annotations.RealmModule class *
-keep class io.realm.internal.Keep
-keep @io.realm.internal.Keep class *
-dontwarn javax.**
-dontwarn io.realm.**

-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }
-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}