package com.example.lemon.notetest.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lemon.notetest.R;
import com.example.lemon.notetest.bean.Person;

import java.io.ByteArrayInputStream;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;

public class LoginActivity extends AppCompatActivity {

    private ImageView image_head;
    private EditText name, password;
    private Button login;
    private TextView regist, forget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // 沉浸式状态栏
//        getWindow()
//                .addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().addFlags(
//                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        setContentView(R.layout.activity_login);
        //初始化Bmob(默认初始化)
        Bmob.initialize(this, "bab127da2c9772c524cf1a76926874a9 ");
        Init();
        onClick();
    }

    private void Init() {
        image_head = (ImageView) findViewById(R.id.head);
        name = (EditText) findViewById(R.id.et_login_name);
        password = (EditText) findViewById(R.id.et_login_pwd);
        login = (Button) findViewById(R.id.button_login);
        regist = (TextView) findViewById(R.id.regist);
        forget = (TextView) findViewById(R.id.forget);
    }

    private void onClick() {
        login.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Login();

            }
        });
        regist.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(intent);

            }
        });
        forget.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "忘记密码",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Login() {
        final String login_name = name.getText().toString();
        final String login_pwd = password.getText().toString();
        //查询单条数据
//        BmobQuery<Person> bmobQuery = new BmobQuery<Person>();
//        bmobQuery.getObject("b99daf5edc", new QueryListener<Person>() {
//            @Override
//            public void done(Person person, BmobException e) {
//                if (e == null){
//                    Log.i("查询成功", person.getUserName()+"*****"+person.getPhoneNum()+"*****"+person.getEmail());
//                }else {
//                    Log.i("查询失败", e.getMessage());
//                }
//            }
//        });
        if (login_name.isEmpty() || login_pwd.isEmpty()) {
            Toast.makeText(getApplicationContext(), "输入不能为空",
                    Toast.LENGTH_SHORT).show();
        } else {
            //查询所有数据
            BmobQuery<Person> query = new BmobQuery<>();
            query.addWhereEqualTo("userName", login_name);
            query.findObjects(new FindListener<Person>() {
                @Override
                public void done(List<Person> list, BmobException e) {
                    for (Person person : list) {
                        if (login_name.equals(person.getUserName()) && login_pwd.equals(person.getPassWord())) {
                            Log.i("Message", "登陆成功");
                            login.setText("登陆中……");
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(), "用户名或者密码错误",
                                    Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }
            });

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
