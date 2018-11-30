package cn.edu.hebtu.software.snow;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.utils.SMSLog;

public class RegisterActivity extends AppCompatActivity {

    //获取电话输入框
    private EditText phoneET;

    //获取短信验证码按钮
    private Button getVerificationCodeBtu;
    //城市信息编号
    private TextView countryInformationTV;
    //电话号码
    private String phone;

    private ImageView wrong;
    private ImageView cho;
    private TextView tv2;
    private TextView tv4;
    private TextView tv4_2;

    private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findView();

        EventHandler eh = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                Message msg = new Message();
                msg.arg1 = event;
                msg.arg2 = result;
                msg.obj = data;
                mHandler.sendMessage(msg);
            }

        };
        SMSSDK.registerEventHandler(eh);
    }


    private void findView() {
        /*
        * 绑定
        * */
        getVerificationCodeBtu = findViewById(R.id.btn_getVerificationCode);
        wrong = findViewById(R.id.iv_wrong);
        phoneET = findViewById(R.id.ed_phone);
        cho = findViewById(R.id.iv_cho);
        tv2 = findViewById(R.id.tv_agree2);
        tv4 = findViewById(R.id.tv_agree4);
        tv4_2 = findViewById(R.id.tv_agree4_2);
        relativeLayout = findViewById(R.id.rel);
        countryInformationTV=findViewById(R.id.tv_cho);

        phoneET.setOnKeyListener(new getFocus());
        cho.setOnClickListener(new setOnClickListener());
        wrong.setOnClickListener(new setOnClickListener());
        tv4_2.setOnClickListener(new setOnClickListener());
        tv4.setOnClickListener(new setOnClickListener());
        tv2.setOnClickListener(new setOnClickListener());
    }
    public class setOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_getVerificationCode:
                    if (v.getContext().toString().length()!=11){
                        //手机号格式不正确，弹出来不正确的消息
                    }else{
                        //获得验证码
                        SMSSDK.getVerificationCode("86", phoneET.getText().toString());
                        phone = phoneET.getText().toString();
                    }
                    break;
                case R.id.iv_cho:
                    Intent intent = new Intent(RegisterActivity.this,ChoseActivity.class);
                    startActivityForResult(intent, 1);
                    break;
                case R.id.iv_wrong:
                    finish();
                    break;
                case R.id.tv_agree2:
                    showPopupWindow();
                    break;
                case R.id.tv_agree4:
                    showPopupWindow();
                    break;
                case R.id.tv_agree4_2:
                    showPopupWindow();
            }
        }
    }

    private void showPopupWindow(){
        final PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        View view = getLayoutInflater().inflate(R.layout.activity_mes,null);
        view.findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY,0,0);
    }

    //ChoseActivity返回城市信息时调用
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    String countryInformationStr = data.getStringExtra("countryInformation");
                    countryInformationTV.setText(countryInformationStr);
                }
                break;
            default:
        }
    }

    public class getFocus implements View.OnKeyListener {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            String username1 = phoneET.getText().toString();
            switch (v.getId()) {
                case R.id.ed_phone:
                    if (keyCode == 67) {
                        if ("".equals(username1)) {
                            getVerificationCodeBtu.setBackgroundColor(getResources().getColor(R.color.colorGray));
                        } else {
                            getVerificationCodeBtu.setBackgroundColor(getResources().getColor(R.color.colorTomato));
                        }
                    } else {
                        if ("".equals(username1)) {
                            getVerificationCodeBtu.setBackgroundColor(getResources().getColor(R.color.colorGray));
                        } else {
                            getVerificationCodeBtu.setBackgroundColor(getResources().getColor(R.color.colorTomato));
                        }
                    }
                    break;
            }
            return false;
        }
    }

    //获得短信线程
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

            // TODO Auto-generated method stub
            super.handleMessage(msg);
            int event = msg.arg1;
            int result = msg.arg2;
            Object data = msg.obj;
            Log.e("event", "event=" + event);
            if (result == SMSSDK.RESULT_COMPLETE) {
                System.out.println("--------result" + event);
                //短信注册成功后，返回MainActivity,然后提示新好友
                if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功后执行以下步骤
                    //Toast.makeText(getApplicationContext(), "提交验证码成功", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    //已经验证
                    Toast.makeText(getApplicationContext(), "验证码已经发送", Toast.LENGTH_SHORT).show();
                } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                    //已经验证
                    Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
//                    textV.setText(data.toString());
                }

            } else {
//				((Throwable) data).printStackTrace();
//				Toast.makeText(MainActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
//					Toast.makeText(MainActivity.this, "123", Toast.LENGTH_SHORT).show();
                int status = 0;
                try {
                    ((Throwable) data).printStackTrace();
                    Throwable throwable = (Throwable) data;

                    JSONObject object = new JSONObject(throwable.getMessage());
                    String des = object.optString("detail");
                    status = object.optInt("status");
                    if (!TextUtils.isEmpty(des)) {
                        Toast.makeText(RegisterActivity.this, des, Toast.LENGTH_SHORT).show();
                        return;
                    }
                } catch (Exception e) {
                    SMSLog.getInstance().w(e);
                }
            }
        }
    };

    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
