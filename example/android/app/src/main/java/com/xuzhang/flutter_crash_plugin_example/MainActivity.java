package com.xuzhang.flutter_crash_plugin_example;


import android.os.Bundle;
import android.support.annotation.NonNull;
//import io.flutter.embedding.android.FlutterActivity;
import io.flutter.app.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugins.GeneratedPluginRegistrant;

//public class MainActivity extends FlutterActivity {
//  @Override
//  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
//
//    GeneratedPluginRegistrant.registerWith(flutterEngine);
//  }
//
//}

public class MainActivity extends FlutterActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    GeneratedPluginRegistrant.registerWith(this);
  }
}
