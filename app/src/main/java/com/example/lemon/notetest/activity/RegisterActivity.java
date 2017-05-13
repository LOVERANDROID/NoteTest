package com.example.lemon.notetest.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lemon.notetest.R;
import com.example.lemon.notetest.bean.Person;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterActivity extends AppCompatActivity {
    private ImageView head;
    private EditText regist_name, regist_pwd, regist_repwd, regist_phone, regist_mail;
    private Button regist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Bmob.initialize(this, "bab127da2c9772c524cf1a76926874a9 ");
        Init();
        onClick();
    }

    private void Init() {
        head = (ImageView) findViewById(R.id.regist_head);
        regist_name = (EditText) findViewById(R.id.et_regist_name);
        regist_pwd = (EditText) findViewById(R.id.et_regist_pwd);
        regist_repwd = (EditText) findViewById(R.id.et_regist_repwd);
        regist_phone = (EditText) findViewById(R.id.et_regist_phone);
        regist_mail = (EditText) findViewById(R.id.et_regist_mail);
        regist = (Button) findViewById(R.id.button_regist);
    }

    private void onClick() {
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Regist();
            }
        });
    }
    private void Regist() {
        String name_str = regist_name.getText().toString();
        String pwd_str = regist_pwd.getText().toString();
        String repwd_str = regist_repwd.getText().toString();
        String phone_str = regist_phone.getText().toString();
        String mail_str = regist_mail.getText().toString();
        if (!name_str.isEmpty() && !pwd_str.isEmpty() && !repwd_str.isEmpty() && !mail_str.isEmpty() && !phone_str.isEmpty()) {
            if (pwd_str.equals(repwd_str)) {
                Person p2 = new Person();
                p2.setUserName(name_str);
                p2.setPassWord(pwd_str);
                p2.setPhoneNum(phone_str);
                p2.setEmail(mail_str);
                p2.save(new SaveListener<String>() {

                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null) {
                            Toast.makeText(getApplicationContext(), "注册成功",
                                    Toast.LENGTH_SHORT).show();
                            Log.i("注册成功信息", s);
                        } else {
                            Toast.makeText(getApplicationContext(), "注册失败",
                                    Toast.LENGTH_SHORT).show();
                            Log.i("注册失败信息", e.getMessage());
                        }
                    }
                });
            }else {
                Toast.makeText(getApplicationContext(), "两次密码不一样",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "输入不能为空",
                    Toast.LENGTH_SHORT).show();

        }
    }
}