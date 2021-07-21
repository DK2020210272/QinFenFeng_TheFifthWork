package com.example.qinfenfeng_the_fifthwork;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private ArrayList array_account_number = new ArrayList();
    private ArrayList array_password = new ArrayList();

    private int flag=1;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //适配状态栏颜色
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); getWindow().setStatusBarColor(ContextCompat.getColor(this,android.R.color.holo_blue_dark));


        //隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        //获取MainActivity传递过来的数据
        Intent intent_getMainActivity = getIntent();
        array_account_number = intent_getMainActivity.getStringArrayListExtra("data_account_number_in");
        array_password = intent_getMainActivity.getStringArrayListExtra("data_password_in");

        //获取EditText
        EditText register_account_number = (EditText) findViewById(R.id.register_account_number);
        EditText register_password = (EditText) findViewById(R.id.register_password);
        EditText register_password_makesure = (EditText) findViewById(R.id.register_password_makesure);
        register_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
        register_password_makesure.setTransformationMethod(PasswordTransformationMethod.getInstance());

        //获取注册按钮
        Button register = (Button) findViewById(R.id.register2);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(register_account_number.getText().toString().length()!=0 && register_password.getText().toString().length()!=0 && register_password_makesure.getText().toString().equals(register_password.getText().toString())){
                    for(int i=0;i<array_account_number.size();i++){
                        if(register_account_number.getText().toString().equals(array_account_number.get(i))){
                            flag = -1;
                        }
                    }
                    if (flag == -1){
                        Toast.makeText(RegisterActivity.this,"该用户已存在，请重新输入",Toast.LENGTH_SHORT).show();
                        flag=1;
                    }else{
                        Toast.makeText(RegisterActivity.this,"注册成功,请返回登录",Toast.LENGTH_SHORT).show();
                        array_account_number.add(register_account_number.getText().toString());
                        array_password.add(register_password.getText().toString());
                    }
                }else{
                    Toast.makeText(RegisterActivity.this,"注册失败，请重新输入",Toast.LENGTH_SHORT).show();
                }
                if(register_account_number.getText().toString()==null) Toast.makeText(RegisterActivity.this,"注册失败，请重新输入",Toast.LENGTH_SHORT).show();
            }
        });
    }
    //返回键返回数据
    @Override
    public void onBackPressed() {
        Intent intent_back = new Intent();
        intent_back.putExtra("data_account_number",array_account_number);
        intent_back.putExtra("data_password",array_password);
        setResult(RESULT_OK,intent_back);
        finish();
    }
}