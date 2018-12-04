package activitytext.example.com.leancloud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVMobilePhoneVerifyCallback;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.RequestMobileCodeCallback;
import com.avos.avoscloud.SaveCallback;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private EditText phone;
    private EditText mima;
    private Button tijiao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AVUser currentUser = AVUser.getCurrentUser();
        currentUser.getObjectId();
        if (currentUser != null) {
            // 跳转到首页
        } else {
            //缓存用户对象为空时，可打开用户注册界面…
        }

        button = findViewById(R.id.button_1);
        phone =(EditText)findViewById(R.id.phone);
        mima = (EditText)findViewById(R.id.mima);
        tijiao = findViewById(R.id.tijiao);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVUser.requestMobilePhoneVerifyInBackground(phone.getText().toString(), new RequestMobileCodeCallback() {
                    @Override
                    public void done(AVException e) {
                        if(e == null){
                            Toast.makeText(MainActivity.this,"发送验证码",Toast.LENGTH_LONG).show();
                        } else {
                            Log.d("SMS", "Send failed!");
                        }
                    }
                });
            }
        });

        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AVUser.verifyMobilePhoneInBackground(mima.getText().toString(), new AVMobilePhoneVerifyCallback() {
                    @Override
                    public void done(AVException e) {
                        if (e == null) {
                            // 验证成功
                            Toast.makeText(MainActivity.this,"验证码验证成功",Toast.LENGTH_LONG).show();
                        } else {
                            Log.d("SMS", "Verified failed!");
                        }
                    }
                });
            }
        });
    }



}
