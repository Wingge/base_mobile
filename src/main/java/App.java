import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.multidex.MultiDexApplication;

import com.google.android.gms.security.ProviderInstaller;
import com.squareup.leakcanary.LeakCanary;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wing on 16/2/4.
 */
public class App extends MultiDexApplication implements Application.ActivityLifecycleCallbacks {


    private Handler handler;
    private static App appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //内存检测
        LeakCanary.install(this);
        appContext = this;
        this.registerActivityLifecycleCallbacks(this);
        handler = new Handler();
        /**异常处理*/
//        CrashHandler.init(this);
//        /**图片工具*/
//        /**网络工具初始*/
        initHttpTool();

//        NetWorkStateReceiver.registerNetStateObserver(this);
    }

    /**
     * 对安全有要求，使用了SSL的才使用
     */
    private void checkAndFixSSL(){
        // Ensure an updated security provider is installed into the system when a new one is
        // available via Google Play services.
        try {
            ProviderInstaller.installIfNeededAsync(getApplicationContext(),
                    new ProviderInstaller.ProviderInstallListener() {
                        @Override
                        public void onProviderInstalled() {
//                            LOGW(TAG, "New security provider installed.");
                        }

                        @Override
                        public void onProviderInstallFailed(int errorCode, Intent intent) {
//                            LOGE(TAG, "New security provider install failed.");
                            // No notification shown there is no user intervention needed.
                        }
                    });
        } catch (Exception ignorable) {
//            LOGE(TAG, "Unknown issue trying to install a new security provider.", ignorable);
        }
    }

    public static App getContextInstance() {
        return appContext;
    }

    private void initHttpTool(){
//        HttpTools.init(this);
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("contentType","application/json");
        headers.put("apiVer", "p1.0.0.1");
        headers.put("appVer", "1.0.0.1");
        headers.put("deviceType", "1");
        headers.put("channelNo", "0096001");
        headers.put("token", "595c0tgZFR3d69BDedde9e51");
//        HttpTools.initHeaders(headers);

    }


    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    public void exit() {
        exit(null);
    }

    public void exit(Activity context) {
//        finishAllActivitys();
        onTerminate();
    }

    /**
     * /**
     * 完全退出app，应用销毁执行(不能保证一定)
     */
    @Override
    public void onTerminate() {
//        new HttpTools(this).cancelAllRequest();
//        FileUtil.clearLocalCache(this);
        super.onTerminate();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
