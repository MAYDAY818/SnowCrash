package cn.edu.hebtu.software.snow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button login;
    private TextView close;
    private TextView regist;
    private TextView que;
    private RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();
        turnRegist();
        username.setOnKeyListener(new getFocus());
        password.setOnKeyListener(new getFocus());
        que.setOnClickListener(new setOnClickListener());
        close.setOnClickListener(new setOnClickListener());
    }
    private void findView(){
        username=findViewById(R.id.et_username);
        password=findViewById(R.id.et_password);
        login=findViewById(R.id.btn_login);
        que=findViewById(R.id.tv_que);
        close = findViewById(R.id.tv_close);
        regist = findViewById(R.id.tv_register);
        relativeLayout = findViewById(R.id.root);
    }
    private void turnRegist(){
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
    public class setOnClickListener implements  View.OnClickListener{
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_que:
                    showPopupWindow();
                    break;
                case R.id.btn_login:
                    checkUP();
                    break;
                case R.id.tv_close:
//                    Intent intent = new Intent(MainActivity.this,TabhostActivity.class);
//                    startActivity(intent);
//                    finish();
            }
        }
    }
    private void checkUP(){
        //和数据库交互检查用户名密码是否匹配
    }
    private void showPopupWindow(){
        final PopupWindow popupWindow = new PopupWindow(this);
        popupWindow.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        View view = getLayoutInflater().inflate(R.layout.popupwindow_layout,null);
        Button button1 = view.findViewById(R.id.btn_findp);
        Button button2 = view.findViewById(R.id.btn_findn);
        Button button3 = view.findViewById(R.id.btn_center);
        Button button4 = view.findViewById(R.id.btn_exit);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //点击找回密码
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //点击找回用户名
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //点击客服中心
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        popupWindow.setContentView(view);
        popupWindow.showAtLocation(relativeLayout, Gravity.NO_GRAVITY,0,0);
    }
    public class  getFocus implements  View.OnKeyListener{
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            String username1 = username.getText().toString();
            String password1 = password.getText().toString();
            switch (v.getId()){
                case R.id.et_username:
                    if(keyCode==67){
                        if ("".equals(username1)|| "".equals( password1)) {
                            login.setBackgroundColor(getResources().getColor(R.color.colorGray));
                        }else{
                            login.setBackgroundColor(getResources().getColor(R.color.colorTomato));
                        }
                    }else{
                        if ("".equals(username1)|| "".equals( password1)) {
                            login.setBackgroundColor(getResources().getColor(R.color.colorGray));
                        }else {
                            login.setBackgroundColor(getResources().getColor(R.color.colorTomato));
                        }
                    }
                    break;
                case R.id.et_password:
                    if(keyCode==67){
                        if ("".equals(username1)||"".equals( password1)) {
                            login.setBackgroundColor(getResources().getColor(R.color.colorGray));
                        }else{
                            login.setBackgroundColor(getResources().getColor(R.color.colorTomato));
                        }
                    }else{
                        if ("".equals(username1)|| "".equals( password1)) {
                            login.setBackgroundColor(getResources().getColor(R.color.colorGray));
                        }else {
                            login.setBackgroundColor(getResources().getColor(R.color.colorTomato));
                        }
                    }
                    break;
            }
            return false;
        }
    }
}
