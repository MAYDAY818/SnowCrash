package cn.edu.hebtu.software.snow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {
    private EditText phone;
    private Button get;
    private TextView chose;
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
        phone.setOnKeyListener(new getFocus());
        cho.setOnClickListener(new setOnClickListener());
        wrong.setOnClickListener(new setOnClickListener());
        tv4_2.setOnClickListener(new setOnClickListener());
        tv4.setOnClickListener(new setOnClickListener());
        tv2.setOnClickListener(new setOnClickListener());
    }
    private void findView() {
        get = findViewById(R.id.btn_get);
        wrong = findViewById(R.id.iv_wrong);
        phone = findViewById(R.id.phone1);
        cho = findViewById(R.id.iv_cho);
        chose = findViewById(R.id.tv_cho);
        tv2 = findViewById(R.id.tv_agree2);
        tv4 = findViewById(R.id.tv_agree4);
        tv4_2 = findViewById(R.id.tv_agree4_2);
        relativeLayout = findViewById(R.id.rel);
    }
    public class setOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_get:
                    if (v.getContext().toString().length()!=11){
                        //手机号格式不正确，弹出来不正确的消息
                    }else{
                        //获得验证码
                    }
                    break;
                case R.id.iv_cho:
                    Intent intent = new Intent();
                    intent.setClass(RegisterActivity.this,ChoseActivity.class);
                    startActivityForResult(intent, 0);
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

    //当某个界面返回响应信息时调用
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 200){
            String user = (String) data.getSerializableExtra("list");
            chose.setText(user);
        }
    }

    public class getFocus implements View.OnKeyListener {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            String username1 = phone.getText().toString();
            switch (v.getId()) {
                case R.id.phone1:
                    if (keyCode == 67) {
                        if ("".equals(username1)) {
                            get.setBackgroundColor(getResources().getColor(R.color.colorGray));
                        } else {
                            get.setBackgroundColor(getResources().getColor(R.color.colorTomato));
                        }
                    } else {
                        if ("".equals(username1)) {
                            get.setBackgroundColor(getResources().getColor(R.color.colorGray));
                        } else {
                            get.setBackgroundColor(getResources().getColor(R.color.colorTomato));
                        }
                    }
                    break;
            }
            return false;
        }
    }

}
