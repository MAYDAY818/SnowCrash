package activitytext.example.com.leancloud;

import android.app.Application;

import com.avos.avoscloud.AVOSCloud;

public class MyLeanCloudApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"vbJ34puMs7aTNLhnvcMVBE43-gzGzoHsz","cUNNypzMh0vssdWP7LOoDVux");
        AVOSCloud.setDebugLogEnabled(true);
    }
}
