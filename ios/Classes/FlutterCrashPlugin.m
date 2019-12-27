#import "FlutterCrashPlugin.h"
#import <Bugly/Bugly.h>
@implementation FlutterCrashPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
    
    //注册方法通道
    FlutterMethodChannel* channel = [FlutterMethodChannel
                                     methodChannelWithName:@"flutter_crash_plugin"
                                     binaryMessenger:[registrar messenger]];
    
    //初始化插件实例 绑定方法通道
    FlutterCrashPlugin* instance = [[FlutterCrashPlugin alloc] init];
    //注册方法通道回调函数
    [registrar addMethodCallDelegate:instance channel:channel];
}

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
    if ([@"setUp" isEqualToString:call.method]) {
        
        //Bugly SDK初始化方法
        NSString *appID = call.arguments[@"app_id"];
        
        [Bugly startWithAppId:appID];
        
    }else if ([@"postException" isEqualToString:call.method]){
        
        // 获取Bugly数据上报所需的各个参数信息
        NSString *mssage = call.arguments[@"crash_message"];
        
        NSString *detail = call.arguments[@"crash_detail"];
        
        NSArray *stack = [detail componentsSeparatedByString:@"\n"];
        
        //调用Bugly数据上报接口
        [Bugly reportExceptionWithCategory:4 name:mssage reason:stack[0] callStack:stack extraInfo:@{} terminateApp:NO];
        
        
        result(@0);
        
    }
    
    
    else {
        result(FlutterMethodNotImplemented);
    }
}

@end
