package com.xuzhang.flutter_crash_plugin;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.tencent.bugly.crashreport.CrashReport;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/**
 * FlutterCrashPlugin
 */
public class FlutterCrashPlugin implements  MethodCallHandler {

    //注册器，通常为MainActivity
     public final Registrar registrar;
     FlutterCrashPlugin(Registrar registrar) { this.registrar = registrar; }



    // This static function is optional and equivalent to onAttachedToEngine. It supports the old
    // pre-Flutter-1.12 Android projects. You are encouraged to continue supporting
    // plugin registration via this function while apps migrate to use the new Android APIs
    // post-flutter-1.12 via https://flutter.dev/go/android-project-migration.
    //
    // It is encouraged to share logic between onAttachedToEngine and registerWith to keep
    // them functionally equivalent. Only one of onAttachedToEngine or registerWith will be called
    // depending on the user's project. onAttachedToEngine or registerWith must both be defined
    // in the same class.

    //注册器 通常为MainActivity
//    public final Registrar registrar;
//
//    private FlutterCrashPlugin(Registrar registrar) {
//
//        this.registrar = registrar;
//    }
//    private FlutterCrashPlugin(Registrar registrar) { this.registrar = registrar; }


    public static void registerWith(Registrar registrar) {
        //注册插件
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_crash_plugin");

        //初始化插件实例 绑定方法通道  并注册方法通道回调函数
        channel.setMethodCallHandler(new FlutterCrashPlugin(registrar));
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("setUp")) {

            //Bugly SDK初始化
            String appID = call.argument("app_id");

            CrashReport.initCrashReport(registrar.activity().getApplicationContext(), appID, true);

            result.success(0);

        } else if (call.method.equals("postException")) {

            //获取Bugly数据上报所需的各个参数信息
            String message = call.argument("crash_message");
            String detail = call.argument("crash_detail");

            //调用Bugly 数据上报接口

            CrashReport.postException(4, "Flutter Exception", message, detail, null);
            result.success(0);

        } else {
            result.notImplemented();
        }
    }




}
