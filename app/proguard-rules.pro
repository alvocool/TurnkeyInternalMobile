# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontwarn okio.**
-dontwarn com.squareup.okhttp3.**
-keep class com.squareup.okhttp3.** { *; }
-keep interface com.squareup.okhttp3.** { *; }
-dontwarn javax.annotation.Nullable
-dontwarn javax.annotation.ParametersAreNonnullByDefault


-keep class * {
        public private *;
    }
     # rxjava
    -keep class rx.schedulers.Schedulers {
        public static <methods>;
    }
    -keep class rx.schedulers.ImmediateScheduler {
        public <methods>;
    }
    -keep class rx.schedulers.TestScheduler {
        public <methods>;
    }
    -keep class rx.schedulers.Schedulers {
        public static ** test();
    }
    -keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
        long producerIndex;
        long consumerIndex;
    }
    -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
        long producerNode;
        long consumerNode;
    }


# keep the specified class members from being removed or renamed
# only if the class is preserved
-keepclassmembers class com.turnkeyafrica.bankassurance.data.model.api.PolicyResponce { *; }
-keepclassmembers class com.turnkeyafrica.bankassurance.data.model.api.MpesaCheckStatusWrapper { *; }
-keepclassmembers class com.turnkeyafrica.bankassurance.data.model.api.QuoteToPolWrapper { *; }
-keepclassmembers class com.turnkeyafrica.bankassurance.data.model.api.InsuranceQuoteResponce.InsuranceQuotation { *; }
-keepclassmembers class com.turnkeyafrica.bankassurance.data.model.api.NotificationsRegWrapper { *; }
-keepclassmembers class com.turnkeyafrica.bankassurance.data.model.api.InsuranceQuoteResponce$InsuranceQuotation { *; }
-keepclassmembers class com.turnkeyafrica.bankassurance.data.model.api.SimpleOtpRequest { *; }
-keepclassmembers class com.turnkeyafrica.bankassurance.data.model.api.AnswerVerify { *; }
-keepclassmembers class com.turnkeyafrica.bankassurance.data.model.api.CredentialsUpdate { *; }
-keepclassmembers class com.turnkeyafrica.bankassurance.data.model.api.ClientRegistrationRequest { *; }
-keepclassmembers class com.turnkeyafrica.bankassurance.data.model.api.UserSecurityQuestions { *; }
